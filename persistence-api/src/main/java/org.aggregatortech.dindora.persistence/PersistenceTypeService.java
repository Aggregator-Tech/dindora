package org.aggregatortech.dindora.persistence;

import org.aggregatortech.dindora.common.io.system.SystemHelper;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class PersistenceTypeService {

  public static final String PERSISTENCE_TYPE_FILE = "file";
  public static final String PERSISTENCE_TYPE_DYNAMO_DB = "dynamoDB";

  @Inject
  ServiceLocator serviceLocator;

  public void setServiceLocator(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  public PersistenceTypeService() {
  }

  public String getPersistenceType() {
    Optional<String> persistenceType = serviceLocator.getService(SystemHelper.class)
        .readConfigurationProperty(PersistenceConfigProperty.PERSISTENCE_TYPE);
    return persistenceType.orElse(PERSISTENCE_TYPE_FILE);
  }
}
