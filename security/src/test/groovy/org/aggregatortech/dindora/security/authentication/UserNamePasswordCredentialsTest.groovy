package org.aggregatortech.dindora.security.authentication

import org.aggregatortech.dindora.exceptions.InvalidCredentialsException
import org.aggregatortech.dindora.exceptions.MessageService
import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials

class UserNamePasswordCredentialsTest extends BaseSpecification {
    def 'Test Validate User'() {
        setup :

        String username = null
        String password = null

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials()

        def isValid

          authCreds.setUserName(username)
        try {

            isValid = authCreds.validateUser()
            assert isValid == false
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == MessageService.USERNAME_NULL_EMPTY

        }
        username = ""
        authCreds.setUserName(username)
        try {
            isValid = authCreds.validateUser()
            assert isValid == false
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == MessageService.USERNAME_NULL_EMPTY

        }

        username = "atul"

        authCreds.setUserName(username)
        isValid = authCreds.validateUser()
        assert isValid == true



    }

    def 'Test Validate Password'() {

        setup :
        String username = null
        String password = null

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials()
        authCreds.setPassword(password)
        def isValid
        try {
            isValid = authCreds.validatePassword()
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == MessageService.PASSWORD_NULL_EMPTY

        }
        password = ""
        authCreds.setPassword(password)
        try {
            isValid = authCreds.validatePassword()
        }
        catch (InvalidCredentialsException ex) {
            assert ex.getErrorCode() == MessageService.PASSWORD_NULL_EMPTY

        }

        username = null
        password = "dddd"
        authCreds.setPassword(password)
        isValid = authCreds.validatePassword()
        assert isValid == true


    }
    def 'Test Validate  User Password creds Using setter methods'() {
        setup :
        String username = "atul"
        String password = "welcomne1"

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials()
        authCreds.setUserName(username)
        authCreds.setPassword(password)
        def isValid


        isValid = authCreds.validate()
        assert isValid == true

    }
    def 'Test Validate  User Password creds Using Constructor '() {
        setup :
        String username = "atul"
        String password = "welcomne1"

        UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials(username,password)

        def isValid


        isValid = authCreds.validate()
        assert isValid == true

    }

def 'Test Equals method '() {
    setup :
    String username = "atul"
    String password = "welcome1"

    UserNamePasswordCredentials authCreds = new UserNamePasswordCredentials(username,password)
    UserNamePasswordCredentials authCreds1 = new UserNamePasswordCredentials("atul1",password)

    def isValid


    isValid = authCreds.equals(authCreds1)
    assert isValid == false

   authCreds1 = new UserNamePasswordCredentials("atul","welcome1")




    isValid = authCreds.equals(authCreds1)
    assert isValid == true

    authCreds1 = new UserNamePasswordCredentials("atul1","welcome")




    isValid = authCreds.equals(authCreds1)
    assert isValid == false
}




}
