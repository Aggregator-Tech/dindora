package org.aggregatortech.dindora.security.authentication.providers;

import org.aggregatortech.dindora.exceptions.IDStoreNotConfiguredException;
import org.aggregatortech.dindora.exceptions.InvalidCredentialsException;
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;

import java.io.IOException;

@Contract
public interface IDProvider {

    void configure() throws IDStoreNotConfiguredException ;
    boolean authenticate(AuthenticationCredentials authCreds) throws InvalidCredentialsException, IOException;
}
