package org.aggregatortech.dindora.topic.persistence;

import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import org.aggregatortech.dindora.persistence.dynamodb.DynamoDbPersistenceService;
import org.aggregatortech.dindora.topic.object.Topic;
import org.jvnet.hk2.annotations.Service;

import java.util.HashMap;

import static org.aggregatortech.dindora.persistence.PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB;

@Service(name = PERSISTENCE_TYPE_DYNAMO_DB)
public class TopicDynamoDbPersistenceService extends DynamoDbPersistenceService<Topic>
    implements TopicPersistenceService {

  private static final String TABLE_NAME = "topic";

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  protected Topic createEntityInstance() {
    return new Topic();
  }

  @Override
  public Topic getTopic(String id) {
    QuerySpec querySpec;
    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":id", id);
    querySpec = new QuerySpec()
                    .withKeyConditionExpression("id = :id")
                    .withValueMap(valueMap);
    return query(querySpec).get(0);
  }
}
