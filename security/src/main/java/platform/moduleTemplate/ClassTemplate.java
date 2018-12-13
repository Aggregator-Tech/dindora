package platform.moduleTemplate;

import org.glassfish.hk2.api.ServiceLocator;
import platform.common.ConfigProperty;
import platform.common.ServiceLocatorHelper;
import platform.common.io.system.SystemHelper;

public class ClassTemplate {
  private final ServiceLocator serviceLocator;

  public ClassTemplate() {
    this.serviceLocator = ServiceLocatorHelper.getServiceLocator();
  }

  public ClassTemplate(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  public String getConfigurationProperty(ConfigProperty configProperty) {
    return serviceLocator.getService(SystemHelper.class)
        .readMandatoryConfigurationProperty(configProperty);
  }

}
