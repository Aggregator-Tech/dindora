package org.aggregatortech.dindora.exception;

import org.aggregatortech.dindora.message.MessageService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class ExceptionService {
  @Inject
  MessageService messageService;

  public <T extends BaseException> T buildException(T exception) {
    if (messageService != null) {
      exception.setErrorMessage(messageService.getMessage(exception.getErrorCode()));
    }
    return exception;
  }
}
