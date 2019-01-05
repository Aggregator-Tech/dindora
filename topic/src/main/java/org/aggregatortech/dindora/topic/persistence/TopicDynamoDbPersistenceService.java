package org.aggregatortech.dindora.topic.persistence;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import org.aggregatortech.dindora.common.object.Entity;
import org.aggregatortech.dindora.persistence.dynamodb.DynamoDbPersistenceService;
import org.aggregatortech.dindora.topic.object.Topic;
import org.jvnet.hk2.annotations.Service;

import static org.aggregatortech.dindora.persistence.PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB;

@Service(name = PERSISTENCE_TYPE_DYNAMO_DB)
public class TopicDynamoDbPersistenceService extends DynamoDbPersistenceService<Topic> {

  @Override
  protected Topic createEntityInstance() {
    return new Topic();
  }

  @Override
  protected Item mapEntityToItem(Topic entity) {
    PrimaryKey primaryKey = new PrimaryKey().addComponent(Entity.AttributeNames.id.toString(), entity.getId());
    Item item = new Item().withPrimaryKey(primaryKey)
        .withString(Topic.AttributeNames.name.toString(), entity.getName())
        .withString(Topic.AttributeNames.description.toString(), entity.getDescription());
    return item;
  }

  @Override
  public String getTableName() {
    return "topic";
  }
}
