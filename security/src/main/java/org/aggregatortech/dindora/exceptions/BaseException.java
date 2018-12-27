package org.aggregatortech.dindora.exceptions;

import org.aggregatortech.dindora.common.ServiceLocatorHelper;

public class BaseException extends Throwable{

    private  String errorCode;
    private  String errorMessage;



    public BaseException(String errCode) {
        super(errCode);
        this.errorCode = errCode;
        this .errorMessage = ServiceLocatorHelper.getServiceLocator().getService(MessageService.class)
                .getErrorMessage(errCode);


    }

    public BaseException(String err, Exception ex) {
        super(err,ex);
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

        return  this.errorMessage;
    }
}
