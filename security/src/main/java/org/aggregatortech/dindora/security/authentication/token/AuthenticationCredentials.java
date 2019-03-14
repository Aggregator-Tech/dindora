package org.aggregatortech.dindora.security.authentication.token;

import org.aggregatortech.dindora.exception.InvalidCredentialsException;

public interface AuthenticationCredentials {



    /**
     * validates the  token
     * @throws InvalidCredentialsException if token is invalid
     */
    boolean validate() throws InvalidCredentialsException;




}
