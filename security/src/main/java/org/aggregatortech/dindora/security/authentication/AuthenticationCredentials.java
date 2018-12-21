package org.aggregatortech.dindora.security.authentication;

public interface AuthenticationCredentials {
     boolean validate() throws InvalidCredentialsException;

}
