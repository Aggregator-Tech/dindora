package org.aggregatortech.dindora.exceptions;

import org.aggregatortech.dindora.exceptions.MessageService;
import org.aggregatortech.dindora.common.ServiceLocatorHelper;

public class InvalidCredentialsException extends BaseException {


    public InvalidCredentialsException(String errCode) {
        super(errCode);
    }
}
