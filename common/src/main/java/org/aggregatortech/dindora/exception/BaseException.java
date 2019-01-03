package org.aggregatortech.dindora.exception;

public abstract class BaseException extends RuntimeException {

  private String errorCode;
  private String errorMessage;

  public BaseException(String errorCode) {
    super(errorCode);
    this.errorCode = errorCode;
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

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
