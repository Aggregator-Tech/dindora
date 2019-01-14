package org.aggregatortech.dindora.security.authentication.token;

import org.aggregatortech.dindora.exception.InvalidCredentialsException;

public interface AuthenticationCredentials {


    boolean validate() throws InvalidCredentialsException;




}
