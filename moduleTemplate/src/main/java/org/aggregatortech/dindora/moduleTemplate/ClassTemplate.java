package org.aggregatortech.dindora.moduleTemplate;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.common.ConfigProperty;
import org.aggregatortech.dindora.common.io.system.SystemHelper;

import javax.inject.Inject;

@Service
public class ClassTemplate {
  private final ServiceLocator serviceLocator;

  @Inject
  public ClassTemplate(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  public String getConfigurationProperty(ConfigProperty configProperty) {
    return serviceLocator.getService(SystemHelper.class)
        .readMandatoryConfigurationProperty(configProperty);
  }

}
