package org.aggregatortech.dindora.topic.service;

import org.aggregatortech.dindora.persistence.PersistenceTypeService;
import org.aggregatortech.dindora.topic.message.bundle.TopicMessages;
import org.aggregatortech.dindora.topic.object.Topic;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.exception.ProcessingException;
import org.aggregatortech.dindora.persistence.PersistenceService;
import javax.inject.Inject;
import java.util.List;

@Service
public class TopicService {
  PersistenceService<Topic> persistenceService;

  @Inject
  public TopicService(ServiceLocator serviceLocator) {
    String persistenceType = serviceLocator.getService(PersistenceTypeService.class)
        .getPersistenceType();
    this.persistenceService = serviceLocator.getService(PersistenceService.class, persistenceType);
  }

  public PersistenceService<Topic> getPersistenceService() {
    return persistenceService;
  }

  private void checkPersistenceService() {
    if (getPersistenceService() == null) {
      throw new ProcessingException(TopicMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.toString());
    }
  }

  public List<Topic> getAllTopics() {
    checkPersistenceService();
    return getPersistenceService().search();
  }

  public Topic createTopic(Topic newTopic) {
    Topic retTopic;
    retTopic = getPersistenceService().create(newTopic);
    return retTopic;
  }
}
