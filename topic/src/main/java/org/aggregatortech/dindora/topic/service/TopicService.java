package org.aggregatortech.dindora.topic.service;

import org.aggregatortech.dindora.topic.message.bundle.TopicMessages;
import org.aggregatortech.dindora.topic.object.Topic;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.exception.object.ProcessingException;
import org.aggregatortech.dindora.persistence.PersistenceService;
import javax.inject.Inject;
import java.util.List;

@Service
public class TopicService {
  PersistenceService<Topic> persistenceService;

  @Inject
  public TopicService(PersistenceService persistenceService) {
    this.persistenceService = persistenceService;
  }

  private void checkPersistenceService() {
    if (persistenceService == null) {
      throw new ProcessingException(TopicMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.toString());
    }
  }

  public List<Topic> getAllTopics() {
    checkPersistenceService();
    return persistenceService.search();
  }

  public Topic createTopic(Topic newTopic) {
    Topic retTopic;
    retTopic = persistenceService.create(newTopic);
    return retTopic;
  }
}
