package org.aggregatortech.dindora.topic.exception;

public enum TopicErrorMessages {
  DINDORA_TOPIC_PERSISTENCE_MISSING("Persistence service is not available.");

  private final String message;

  TopicErrorMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}