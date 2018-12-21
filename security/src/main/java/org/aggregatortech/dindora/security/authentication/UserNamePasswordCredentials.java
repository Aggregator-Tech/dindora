package org.aggregatortech.dindora.security.authentication;

import org.aggregatortech.dindora.exceptions.ErrorMessageService;

public class UserNamePasswordCredentials  implements AuthenticationCredentials {


    private final String username;
    private final String password;

    @Override
    public boolean validate() throws InvalidCredentialsException{
        return false;
    }

    public boolean validateUser() throws InvalidCredentialsException {
        if ( username == null )
        {
            throw new InvalidCredentialsException(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01);
        }
        if ( username.isEmpty() )
        {
            throw new InvalidCredentialsException(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01);
        }

        return true;
    }

    public UserNamePasswordCredentials (String username, String password) {
        this.username=username;
        this.password= password;

    }

    public boolean validatePassword() throws InvalidCredentialsException {
        if ( password == null )
        {
            throw new InvalidCredentialsException(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_02);
        }
        if ( password.isEmpty() )
        {
            throw new InvalidCredentialsException(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_02);
        }

        return true;
    }
}
