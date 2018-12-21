package org.aggregatortech.dindora.exception.object;

import org.aggregatortech.dindora.exception.service.ErrorMessageService;
import platform.common.ServiceLocatorHelper;

public class BaseException extends RuntimeException {


  private String errorCode;
  private String errorMessage;

  public BaseException(String errorCode) {
    super(errorCode);
    this.errorCode = errorCode;
    this.errorMessage = ServiceLocatorHelper.getServiceLocator()
        .getService(ErrorMessageService.class)
        .getErrorMessage(errorCode);
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
