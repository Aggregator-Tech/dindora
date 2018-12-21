package org.aggregatortech.dindora.topic.service;

import org.aggregatortech.dindora.topic.exception.TopicErrorMessages;
import org.aggregatortech.dindora.topic.object.Topic;
import org.aggregatortech.dindora.topic.persist.PersistenceService;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.exception.object.ProcessingException;

import javax.inject.Inject;
import java.util.List;

@Service
public class TopicService {
  PersistenceService persistenceService;

  @Inject
  public TopicService(PersistenceService persistenceService) {
    this.persistenceService = persistenceService;
  }

  private void checkPersistenceService() {
    if (persistenceService == null) {
      throw new ProcessingException(TopicErrorMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.toString());
    }
  }

  public List<Topic> getAllTopics() {
    checkPersistenceService();
    return persistenceService.search();
  }
}
