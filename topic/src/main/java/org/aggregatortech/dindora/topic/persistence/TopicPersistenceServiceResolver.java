package org.aggregatortech.dindora.topic.persistence;

import org.aggregatortech.dindora.persistence.PersistenceService;
import org.aggregatortech.dindora.persistence.PersistenceTypeService;
import org.aggregatortech.dindora.topic.object.Topic;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class TopicPersistenceServiceResolver {
  @Inject
  PersistenceTypeService persistenceTypeService;
  @Inject
  ServiceLocator serviceLocator;

  public PersistenceService<Topic> resolve() {
    String persistenceType = persistenceTypeService.getPersistenceType();
    PersistenceService<Topic> persistenceService =
        (PersistenceService<Topic>) serviceLocator.getService(PersistenceService.class, persistenceType);
    return persistenceService;
  }
}