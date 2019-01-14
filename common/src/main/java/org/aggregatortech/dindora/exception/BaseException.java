package org.aggregatortech.dindora.exception;

import org.aggregatortech.dindora.common.ServiceLocatorHelper;
import org.aggregatortech.dindora.message.MessageService;

public abstract class BaseException extends RuntimeException {

  private String errorCode;
  private String errorMessage;

  public BaseException(String errCode) {
    super(errCode);
    this.errorCode = errCode;
    MessageService messageService = ServiceLocatorHelper.getServiceLocator().getService(MessageService.class);
    this.errorMessage = messageService.getMessage(this.getErrorCode());

  }

  public BaseException(String errCode, Exception ex) {
    super(errCode,ex);
    this.errorCode = errCode;
    MessageService messageService = ServiceLocatorHelper.getServiceLocator().getService(MessageService.class);
    this.errorMessage = messageService.getMessage(this.getErrorCode());

  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {

    if (this.getErrorCode() == null || this.getErrorCode().isEmpty()) {

      return null;
    }

    if (this.errorMessage == null || this.errorMessage.isEmpty()) {
      return null;
    }

    return this.errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
