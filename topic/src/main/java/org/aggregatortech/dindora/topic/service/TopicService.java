package org.aggregatortech.dindora.topic.service;

import org.aggregatortech.dindora.common.service.BaseService;
import org.aggregatortech.dindora.topic.interceptor.annotation.RequiresAuthorization;
import org.aggregatortech.dindora.topic.message.bundle.TopicMessages;
import org.aggregatortech.dindora.topic.object.Topic;
import org.aggregatortech.dindora.topic.persistence.TopicPersistenceService;
import org.aggregatortech.dindora.topic.persistence.TopicPersistenceServiceResolver;
import org.glassfish.hk2.extras.interception.Intercepted;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.exception.ProcessingException;

import javax.inject.Inject;
import java.util.List;

@Service
@Intercepted
public class TopicService extends BaseService {
  @Inject
  TopicPersistenceServiceResolver topicPersistenceServiceResolver;
  TopicPersistenceService persistenceService;

  public void setPersistenceService(TopicPersistenceService persistenceService) {
    this.persistenceService = persistenceService;
  }

  public TopicPersistenceService getPersistenceService() {
    if (persistenceService == null && topicPersistenceServiceResolver != null) {
      persistenceService = topicPersistenceServiceResolver.resolve();
    }
    if (persistenceService == null) {
      throw new ProcessingException(TopicMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.toString());
    }
    return persistenceService;
  }

  public List<Topic> getAllTopics() {
    return getPersistenceService().getAll();
  }

  @RequiresAuthorization
  public Topic getTopic(String id) {
    return getPersistenceService().get(id);
  }

  public Topic createTopic(Topic newTopic) {
    Topic retTopic;
    retTopic = getPersistenceService().create(newTopic);
    return retTopic;
  }
}
