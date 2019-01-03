package org.aggregatortech.dindora.topic.service;

import org.aggregatortech.dindora.common.service.BaseService;
import org.aggregatortech.dindora.topic.message.bundle.TopicMessages;
import org.aggregatortech.dindora.topic.object.Topic;
import org.aggregatortech.dindora.topic.persistence.TopicPersistenceServiceResolver;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.exception.ProcessingException;
import org.aggregatortech.dindora.persistence.PersistenceService;
import javax.inject.Inject;
import java.util.List;

@Service
public class TopicService extends BaseService {
  @Inject
  TopicPersistenceServiceResolver topicPersistenceServiceResolver;
  PersistenceService<Topic> persistenceService;

  public void setPersistenceService(PersistenceService<Topic> persistenceService) {
    this.persistenceService = persistenceService;
  }

  public PersistenceService<Topic> getPersistenceService() {
    if (persistenceService == null && topicPersistenceServiceResolver != null) {
      persistenceService = topicPersistenceServiceResolver.resolve();
    }
    return persistenceService;
  }

  private void checkPersistenceService() {
    if (getPersistenceService() == null) {
      throw getExceptionService().buildException(new ProcessingException(TopicMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.toString()));
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
