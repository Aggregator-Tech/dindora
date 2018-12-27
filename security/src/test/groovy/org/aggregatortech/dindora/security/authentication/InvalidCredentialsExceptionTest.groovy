package org.aggregatortech.dindora.security.authentication

import org.aggregatortech.dindora.common.ServiceLocatorHelper
import org.aggregatortech.dindora.exceptions.InvalidCredentialsException
import org.aggregatortech.dindora.exceptions.MessageService
import org.aggregatortech.dindora.common.test.BaseSpecification


class InvalidCredentialsExceptionTest extends BaseSpecification {

    def 'Test Exception has error code and error message'() {
        setup :

        String errorCode = MessageService.USERNAME_NULL_EMPTY;
        InvalidCredentialsException exception = new InvalidCredentialsException( errorCode);
        MessageService msgService = ServiceLocatorHelper.getServiceLocator().getService(MessageService.class);

        assert exception.getErrorMessage().indexOf (msgService.getErrorMessage(errorCode)) != -1
        assert exception.getErrorCode().indexOf (MessageService.USERNAME_NULL_EMPTY) != -1


    }

    def 'Test Exception has null, empty and invalid  error code and error message should be null'() {
        setup :

        String errorCode = null;
        InvalidCredentialsException exception = new InvalidCredentialsException( errorCode);

        assert exception.getErrorMessage() == null
        assert exception.getErrorCode() == null

        exception = new InvalidCredentialsException( "");
        assert exception.getErrorMessage() == null
        assert exception.getErrorCode() == ""

        exception = new InvalidCredentialsException( "1233");
        assert exception.getErrorMessage() == null
        assert exception.getErrorCode() == "1233"


    }
}
