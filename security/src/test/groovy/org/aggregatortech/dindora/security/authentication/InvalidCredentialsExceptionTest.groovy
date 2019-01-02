package org.aggregatortech.dindora.security.authentication

import org.aggregatortech.dindora.exceptions.ErrorMessageService
import org.aggregatortech.dindora.common.test.BaseSpecification


class InvalidCredentialsExceptionTest extends BaseSpecification {

    def 'Test Exception has error code and error message'() {
        setup :

        String errorCode = ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01;
        InvalidCredentialsException exception = new InvalidCredentialsException( errorCode);

        assert exception.getErrorMessage().indexOf (ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01_MSG) != -1
        assert exception.getErrorCode().indexOf (ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01) != -1


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
