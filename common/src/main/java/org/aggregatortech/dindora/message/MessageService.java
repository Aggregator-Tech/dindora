package org.aggregatortech.dindora.message;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class MessageService {
  private final ServiceLocator serviceLocator;
  private final MessageBundleNameService messageBundleNameService;

  @Inject
  public MessageService(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
    this.messageBundleNameService = serviceLocator.getService(MessageBundleNameService.class);
  }

  public String getMessage(String errCode) {
    return serviceLocator.getService(MessageBundle.class, messageBundleNameService.getMessageBundleName(errCode))
        .getMessage(errCode);
  }
}
