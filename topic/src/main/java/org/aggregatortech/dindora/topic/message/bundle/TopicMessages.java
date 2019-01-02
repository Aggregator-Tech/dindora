package org.aggregatortech.dindora.topic.message.bundle;

public enum TopicMessages {
  DINDORA_TOPIC_PERSISTENCE_MISSING("Persistence service is not available.");

  private final String message;

  TopicMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}