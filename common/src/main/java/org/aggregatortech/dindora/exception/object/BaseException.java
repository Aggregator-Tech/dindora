package org.aggregatortech.dindora.exception.object;

import org.aggregatortech.dindora.message.MessageService;
import platform.common.ServiceLocatorHelper;

public abstract class BaseException extends RuntimeException {

  private String errorCode;
  private String errorMessage;

  public BaseException(String errorCode) {
    super(errorCode);
    this.errorCode = errorCode;
    this.errorMessage = ServiceLocatorHelper.getServiceLocator()
        .getService(MessageService.class)
        .getMessage(errorCode);
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

    return this.errorCode + "  :  " + this.errorMessage;
  }
}
