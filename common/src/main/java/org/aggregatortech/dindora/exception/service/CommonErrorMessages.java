package org.aggregatortech.dindora.exception.service;

public enum CommonErrorMessages {
  DINDORA_COMMON_PROCESSING_FAILED("Unable to process the request.");

  private final String message;

  CommonErrorMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}