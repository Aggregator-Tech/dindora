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
import org.aggregatortech.dindora.common.service.BaseService;
import org.aggregatortech.dindora.common.service.IdGenerationService;
import org.aggregatortech.dindora.persistence.PersistenceService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DynamoDbPersistenceService<T extends Entity>
    extends BaseService
    implements PersistenceService<T> {

  @Inject
  private IdGenerationService idGenerationService;

  protected DynamoDB dynamoDb;
  private Table table;

  public DynamoDbPersistenceService() {
    Regions region = Regions.fromName("us-west-2");
    System.setProperty("aws.accessKeyId","AKIAIQ7CJZDSLL6V6TXA");
    System.setProperty("aws.secretKey","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");
    AmazonDynamoDB client;
    client = AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
    dynamoDb = new DynamoDB(client);
    table = dynamoDb.getTable(getTableName());
  }

  public List<T> search() {
    List<T> entities = new ArrayList<T>();
    ScanSpec scanSpec = new ScanSpec();
    ItemCollection<ScanOutcome> items = table.scan(scanSpec);
    Iterator<Item> iter = items.iterator();
    while (iter.hasNext()) {
      T entity = createEntityInstance();
      Item item = iter.next();
      item.attributes().forEach(entry -> entity.getAttributes().put(entry.getKey(), entry.getValue().toString()));
      entities.add(entity);
    }
    return entities;
  }

  protected abstract T createEntityInstance();

  protected abstract Item mapEntityToItem(T entity);

  public abstract String getTableName();

  public T create(T entity) {
    entity.setId(idGenerationService.generate());
    Item item = mapEntityToItem(entity);
    table.putItem(item);
    return entity;
  }

}
