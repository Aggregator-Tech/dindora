package org.aggregatortech.dindora.topic.persistence;

import org.aggregatortech.dindora.persistence.PersistenceService;
import org.aggregatortech.dindora.persistence.PersistenceTypeService;
import org.aggregatortech.dindora.topic.object.Topic;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class TopicPersistenceServiceFactory implements Factory<PersistenceService<Topic>> {
  @Inject
  PersistenceTypeService persistenceTypeService;
  @Inject
  ServiceLocator serviceLocator;

  @Override
  public PersistenceService<Topic> provide() {
    String persistenceType = persistenceTypeService.getPersistenceType();
    PersistenceService persistenceService =
        serviceLocator.getService(PersistenceService.class, persistenceType);
    return persistenceService;
  }

  @Override
  public void dispose(PersistenceService<Topic> instance) {

  }
}
