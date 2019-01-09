package org.aggregatortech.dindora.topic.message.bundle;

public enum TopicMessages {
  DINDORA_TOPIC_PERSISTENCE_MISSING("Topic Persistence service is not available."),
  DINDORA_TOPIC_PERSISTENCE_FAILED("Communication failure with Persistence service.");

  private final String message;

  TopicMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}