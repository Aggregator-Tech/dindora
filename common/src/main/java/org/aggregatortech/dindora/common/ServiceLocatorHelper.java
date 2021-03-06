package org.aggregatortech.dindora.common;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.aggregatortech.dindora.exception.ProcessingException;
import org.aggregatortech.dindora.message.bundle.CommonMessages;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.extras.ExtrasUtilities;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

import java.io.IOException;

public class ServiceLocatorHelper {

  public static ServiceLocator getServiceLocator() {
    return serviceLocator;
  }

  public static void setServiceLocator(ServiceLocator serviceLocator) {
    ServiceLocatorHelper.serviceLocator = serviceLocator;
  }

  private static ServiceLocator serviceLocator;

  static {
    populateServices();
  }

  private static void populateServices() {
    try {
      setServiceLocator(ServiceLocatorUtilities.createAndPopulateServiceLocator());

      ImmutableSet<ClassPath.ClassInfo> allClasses =
          ClassPath.from(ClassLoader.getSystemClassLoader())
              .getTopLevelClassesRecursive(Constants.BASE_PACKAGE);
      Class<?>[] serviceClasses = allClasses.stream()
          .map(ClassPath.ClassInfo::load)
          .filter(classObject -> classObject.isAnnotationPresent(Service.class))
          .toArray(Class[]::new);
      ServiceLocatorUtilities.addClasses(getServiceLocator(), serviceClasses);
      ExtrasUtilities.enableDefaultInterceptorServiceImplementation(getServiceLocator());

    } catch (IOException e) {
      e.printStackTrace();
      throw new ProcessingException(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString());
    }
  }

}
