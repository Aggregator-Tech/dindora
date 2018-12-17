package org.aggregatortech.dindora.security.authentication;

import org.aggregatortech.dindora.exceptions.ErrorMessageService;

public class InvalidCredentialsException extends Exception {


    private  String errorCode;
    private  String errorMessage;

    public InvalidCredentialsException(String errCode) {
        super(errCode);
        this.errorCode = errCode;
        this .errorMessage = ErrorMessageService.getInstance().getErrorMessage(errCode);

    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {

        if (this.getErrorCode() == null || this.getErrorCode().isEmpty() ) {

            return null;
        }

        if (this.errorMessage == null || this.errorMessage.isEmpty()) {
            return null;
        }

        return this.errorCode +  "  :  " + this.errorMessage;
    }
}
