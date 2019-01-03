package org.aggregatortech.dindora.persistence.file;

import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.common.io.system.SystemHelper;

import javax.inject.Inject;

@Service
public class FilePersistenceLocationService {

  @Inject
  SystemHelper systemHelper;

  public String getLocation() {
    return systemHelper.readMandatoryConfigurationProperty(FilePersistenceConfigProperty.FILE_PERSISTENCE_LOCATION);
  }
}
