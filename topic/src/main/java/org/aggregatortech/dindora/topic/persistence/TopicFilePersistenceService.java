package org.aggregatortech.dindora.topic.persistence;

import com.fasterxml.jackson.databind.type.CollectionType;
import org.aggregatortech.dindora.exception.object.ProcessingException;
import org.aggregatortech.dindora.persistence.FilePersistenceService;
import org.aggregatortech.dindora.topic.object.Topic;
import org.glassfish.hk2.api.ServiceLocator;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class TopicFilePersistenceService extends FilePersistenceService<Topic> {

  @Inject
  public TopicFilePersistenceService(ServiceLocator serviceLocator) {
    super(serviceLocator);
  }

  @Override
  protected List<Topic> readData() {
    List<Topic> topics = null;
    try {
      CollectionType javaType = getMapper().getTypeFactory()
          .constructCollectionType(List.class, Topic.class);
      topics = getMapper().readValue(getPersistenceFile(), javaType);
    } catch (IOException e) {
      // TODO throw appropriate ProcessingException
      throw new RuntimeException(e.getMessage());
    }
    return topics;
  }

  @Override
  protected void writeData() {
    try {
      getMapper().writeValue(getPersistenceFile(), getEntities());
    } catch (IOException e) {
      // TODO throw appropriate ProcessingException
      throw new RuntimeException(e.getMessage());
    }
  }
}
