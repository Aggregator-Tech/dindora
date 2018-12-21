package org.aggregatortech.dindora.exception.service;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class ErrorMessageService {
  private final ServiceLocator serviceLocator;
  private final ErrorBundleNameService errorBundleNameService;

  @Inject
  public ErrorMessageService(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
    this.errorBundleNameService = serviceLocator.getService(ErrorBundleNameService.class);
  }

  public String getErrorMessage(String errCode) {
    return serviceLocator.getService(ErrorMessageBundle.class, errorBundleNameService.getErrorBundleName(errCode))
        .getMessage(errCode);
  }
}
