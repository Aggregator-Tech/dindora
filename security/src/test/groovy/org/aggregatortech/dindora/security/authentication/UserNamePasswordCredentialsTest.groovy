package org.aggregatortech.dindora.security.authentication

import org.aggregatortech.dindora.exceptions.ErrorMessageService
import org.aggregatortech.dindora.common.test.BaseSpecification

class UserNamePasswordCredentialsTest extends BaseSpecification {
    def 'Test Validate User'() {
        setup :

        String username = null
        String password = null

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials(username, password)

        def isValid
        try {
            isValid = authCreds.validateUser()
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01

        }
        username = ""
        authCreds = new UserNamePasswordCredentials(username, password)
        try {
            isValid = authCreds.validateUser()
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01

        }

        username = "atul"
        password = null
        authCreds = new UserNamePasswordCredentials(username, password)
        isValid = authCreds.validateUser()
        assert isValid == true



    }

    def 'Test Validate Password'() {

        setup :
        String username = null
        String password = null

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials(username, password)
        def isValid
        try {
            isValid = authCreds.validatePassword()
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == ErrorMessageService.DINDORA_SECURITY_ERR_CODE_02

        }
        password = ""
        authCreds = new UserNamePasswordCredentials(username, password)
        try {
            isValid = authCreds.validatePassword()
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == ErrorMessageService.DINDORA_SECURITY_ERR_CODE_02

        }

        username = null
        password = "dddd"
        authCreds = new UserNamePasswordCredentials(username, password)
        isValid = authCreds.validatePassword()
        assert isValid == true


    }
    def 'Test Validate  User Password creds'() {
        setup :
        String username = null
        String password = null

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials(username, password)
        def isValid

            isValid = authCreds.validate()
        assert isValid == false

    }

}
