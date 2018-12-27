package org.aggregatortech.dindora.security.authentication.token;

import org.aggregatortech.dindora.exceptions.InvalidCredentialsException;

public interface AuthenticationCredentials {


    boolean validate() throws InvalidCredentialsException;
    boolean equals(AuthenticationCredentials authenticationCredentials);




}
