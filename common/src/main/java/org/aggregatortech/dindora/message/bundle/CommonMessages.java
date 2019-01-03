package org.aggregatortech.dindora.message.bundle;

public enum CommonMessages {
  DINDORA_COMMON_PROCESSING_FAILED("Unable to process the request.");

  private final String message;

  CommonMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}