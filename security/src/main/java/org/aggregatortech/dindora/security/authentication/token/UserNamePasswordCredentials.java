package org.aggregatortech.dindora.security.authentication.token;

import org.aggregatortech.dindora.exception.InvalidCredentialsException;

import org.aggregatortech.dindora.security.bundle.SecurityMessages;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNamePasswordCredentials that = (UserNamePasswordCredentials) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }



    public boolean validateUser() throws InvalidCredentialsException {
        if ( username == null ) {
            throw new InvalidCredentialsException(SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString());
        }
        if ( username.isEmpty() )
        {
            throw new InvalidCredentialsException(SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString());
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
            throw new InvalidCredentialsException(SecurityMessages.DINDORA_SECURITY_PASSWORD_NULL_EMPTY.toString());
        }
        if ( password.isEmpty() )
        {
            throw new InvalidCredentialsException(SecurityMessages.DINDORA_SECURITY_PASSWORD_NULL_EMPTY.toString());
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
