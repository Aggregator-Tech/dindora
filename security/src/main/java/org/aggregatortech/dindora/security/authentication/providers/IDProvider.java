package org.aggregatortech.dindora.security.authentication.providers;

import org.aggregatortech.dindora.exception.InvalidCredentialsException;
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IDProvider {


    boolean authenticate(AuthenticationCredentials authCreds) throws InvalidCredentialsException;
}
