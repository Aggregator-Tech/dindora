package org.aggregatortech.dindora.security.bundle;

public enum SecurityMessages {

    DINDORA_SECURITY_PASSWORD_NULL_EMPTY("Password is Null or Empty"),
    DINDORA_SECURITY_USERNAME_NULL_EMPTY("User Name is Null or Empty"),
    DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED("ID Store not found or configured"),
    DINDORA_SECURITY_IDSTORE_FORMAT_NOT_VALID("IDStore format not valid");

    private final String message;

    SecurityMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
