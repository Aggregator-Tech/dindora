package org.aggregatortech.dindora.persistence.file;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.common.io.system.SystemHelper;

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
