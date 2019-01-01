package org.aggregatortech.dindora.persistence;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import platform.common.ServiceLocatorHelper;
import platform.common.io.system.SystemHelper;

import javax.inject.Inject;

@Service
public class FilePersistenceLocationService {
  ServiceLocator serviceLocator;

  @Inject
  public FilePersistenceLocationService(ServiceLocator serviceLocator) {
  }

  public String getLocation() {
    return serviceLocator.getService(SystemHelper.class)
        .readMandatoryConfigurationProperty(FilePersistenceConfigProperty.FILE_PERSISTENCE_LOCATION);
  }
}
