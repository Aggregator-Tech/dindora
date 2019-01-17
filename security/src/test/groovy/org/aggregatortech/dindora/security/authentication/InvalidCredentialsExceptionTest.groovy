package org.aggregatortech.dindora.security.authentication


import org.aggregatortech.dindora.common.ServiceLocatorHelper
import org.aggregatortech.dindora.exception.InvalidCredentialsException

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.message.MessageService
import org.aggregatortech.dindora.security.bundle.SecurityMessages


class InvalidCredentialsExceptionTest extends BaseSpecification {

    def 'Test Exception has error code and error message'() {
        setup :

        String errorCode = SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString();
        InvalidCredentialsException exception = new InvalidCredentialsException( errorCode);
        MessageService msgService = ServiceLocatorHelper.getServiceLocator().getService(MessageService.class);

        assert exception.getErrorMessage().equals(msgService.getMessage(errorCode))
        assert exception.getErrorCode().indexOf (SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString()) != -1


    }


}
