package org.aggregatortech.dindora.persistence.message.bundle;

public enum PersistenceMessages {
  DINDORA_PERSISTENCE_NOT_CONFIGURED("Persistence service is not configured properly.");

  private final String message;

  PersistenceMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}