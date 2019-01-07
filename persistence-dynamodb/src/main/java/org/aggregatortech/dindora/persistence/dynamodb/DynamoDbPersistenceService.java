package org.aggregatortech.dindora.persistence.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import org.aggregatortech.dindora.common.object.Entity;
import org.aggregatortech.dindora.common.service.AwsRegionService;
import org.aggregatortech.dindora.common.service.BaseService;
import org.aggregatortech.dindora.common.service.IdGenerationService;
import org.aggregatortech.dindora.persistence.PersistenceService;

import javax.inject.Inject;
import java.util.ArrayList;
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

  protected DynamoDB dynamoDb;

  public DynamoDbPersistenceService() {
  }

  public Table getTable() {
    if (dynamoDb == null) {
      synchronized (this) {
        if (dynamoDb == null) {
          Regions region = Regions.fromName(awsRegionService.getRegion());
          AmazonDynamoDB client;
          client = AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
          dynamoDb = new DynamoDB(client);
        }
      }
    }
    Table table = dynamoDb.getTable(getTableName());
    return table;
  }

  public abstract String getTableName();

  public List<T> search() {
    ScanSpec scanSpec = new ScanSpec();
    ItemCollection<ScanOutcome> items = getTable().scan(scanSpec);
    Iterator<Item> iter = items.iterator();
    return mapItemsToEntities(iter);
  }

  protected List<T> mapItemsToEntities(Iterator<Item> iter) {
    List<T> entities = new ArrayList<T>();
    while (iter.hasNext()) {
      T entity = createEntityInstance();
      Item item = iter.next();
      item.attributes().forEach(entry -> entity.getAttributes().put(entry.getKey(), entry.getValue().toString()));
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
    Set<String> attributes = entity.getAttributes().keySet();
    for (String attribute : attributes) {
      item = item.withString(attribute, entity.getAttributes().get(attribute));
    }
    return item;
  }

}
