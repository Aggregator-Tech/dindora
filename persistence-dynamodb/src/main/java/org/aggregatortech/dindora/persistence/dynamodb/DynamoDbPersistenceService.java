package org.aggregatortech.dindora.persistence.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import org.aggregatortech.dindora.common.object.Entity;
import org.aggregatortech.dindora.common.service.AwsRegionService;
import org.aggregatortech.dindora.common.service.BaseService;
import org.aggregatortech.dindora.common.service.IdGenerationService;
import org.aggregatortech.dindora.exception.ProcessingException;
import org.aggregatortech.dindora.persistence.PersistenceService;
import org.aggregatortech.dindora.persistence.message.bundle.PersistenceMessages;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class DynamoDbPersistenceService<T extends Entity>
    extends BaseService
    implements PersistenceService<T> {

  @Inject
  private IdGenerationService idGenerationService;
  @Inject
  private AwsRegionService awsRegionService;
  @Inject
  private DynamoDbHelper dynamoDbHelper;

  protected DynamoDB dynamoDb;

  public DynamoDbPersistenceService() {
  }

  public Table getTable() {
    if (dynamoDb == null) {
      synchronized (this) {
        if (dynamoDb == null) {
          selfCheck();
          String regionName = awsRegionService.getRegion();

          AmazonDynamoDB client;
          AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();

          if(regionName != null) {
            Regions region = Regions.fromName(regionName);
            clientBuilder = clientBuilder.withRegion(region);
          }
          client = clientBuilder.build();
          dynamoDb = new DynamoDB(client);
        }
      }
    }
    Table table = dynamoDb.getTable(getTableName());
    return table;
  }

  public abstract String getTableName();

  public List<T> getAll() {
    ScanSpec scanSpec = new ScanSpec();
    ItemCollection<ScanOutcome> items = getTable().scan(scanSpec);
    Iterator<Item> iter = items.iterator();
    return mapItemsToEntities(iter);
  }

  public List<T> query(QuerySpec querySpec) {
    ItemCollection<QueryOutcome> items = getTable().query(querySpec);
    Iterator<Item> iter = items.iterator();
    return mapItemsToEntities(iter);
  }

  protected List<T> mapItemsToEntities(Iterator<Item> iter) {
    List<T> entities = new ArrayList<T>();
    while (iter.hasNext()) {
      T entity = createEntityInstance();
      Item item = iter.next();
      item.attributes().forEach(entry -> entity.allAttributes().put(entry.getKey(), entry.getValue().toString()));
      entities.add(entity);
    }
    return entities;
  }

  protected abstract T createEntityInstance();

  public T create(T entity) {
    entity = beforeCreate(entity);
    Item item = mapEntityToItem(entity);
    getTable().putItem(item);
    return entity;
  }

  protected T beforeCreate(T entity) {
    entity.setId(idGenerationService.generate());
    return entity;
  }

  protected Item mapEntityToItem(T entity) {
    Item item = new Item();
    Set<String> attributes = entity.allAttributes().keySet();
    for (String attribute : attributes) {
      item = item.withString(attribute, entity.allAttributes().get(attribute));
    }
    return item;
  }

  @Override
  public T get(String id) {
    QuerySpec querySpec;
    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":id", id);
    querySpec = new QuerySpec()
                    .withKeyConditionExpression("id = :id")
                    .withValueMap(valueMap);
    return query(querySpec).get(0);
  }

  @Override
  public void selfCheck() {
    if (!dynamoDbHelper.checkConfiguration()) {
      throw new ProcessingException(PersistenceMessages.DINDORA_PERSISTENCE_NOT_CONFIGURED.toString());
    }
  }
}
