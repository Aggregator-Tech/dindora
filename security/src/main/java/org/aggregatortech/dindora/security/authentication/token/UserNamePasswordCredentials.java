package org.aggregatortech.dindora.security.authentication.token;

import org.aggregatortech.dindora.exceptions.MessageService;
import org.aggregatortech.dindora.exceptions.InvalidCredentialsException;

public class UserNamePasswordCredentials  implements AuthenticationCredentials {


    private  String username;
    private  String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean validate() throws InvalidCredentialsException {
       return validateUser() && validatePassword();


    }

    @Override
    public boolean equals(AuthenticationCredentials authenticationCredentials) {
        UserNamePasswordCredentials upc = (UserNamePasswordCredentials)authenticationCredentials;

        if (this.username.equals(upc.getUsername()) && this.password.equals(upc.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean validateUser() throws InvalidCredentialsException {
        if ( username == null )
        {
            throw new InvalidCredentialsException(MessageService.USERNAME_NULL_EMPTY);
        }
        if ( username.isEmpty() )
        {
            throw new InvalidCredentialsException(MessageService.USERNAME_NULL_EMPTY);
        }

        return true;
    }

    public UserNamePasswordCredentials (String username, String password) throws InvalidCredentialsException {
        this.username=username;
        this.password= password;
         validate();

    }
    public UserNamePasswordCredentials (){

    }

    public boolean validatePassword() throws InvalidCredentialsException {
        if ( password == null )
        {
            throw new InvalidCredentialsException(MessageService.PASSWORD_NULL_EMPTY);
        }
        if ( password.isEmpty() )
        {
            throw new InvalidCredentialsException(MessageService.PASSWORD_NULL_EMPTY);
        }

        return true;
    }


    public void setUserName(String  username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
