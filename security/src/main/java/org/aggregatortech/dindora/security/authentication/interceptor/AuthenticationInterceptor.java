package org.aggregatortech.dindora.security.authentication.interceptor;

import org.aggregatortech.dindora.common.ServiceLocatorHelper;
import org.aggregatortech.dindora.exceptions.IDStoreNotConfiguredException;
import org.aggregatortech.dindora.exceptions.InvalidCredentialsException;
import org.aggregatortech.dindora.exceptions.MessageService;
import org.aggregatortech.dindora.security.authentication.providers.IDProvider;
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;

import java.io.IOException;

public class AuthenticationInterceptor {


    private final IDProvider provider;

    public AuthenticationInterceptor() throws IDStoreNotConfiguredException {

        provider = ServiceLocatorHelper.getServiceLocator().getService(IDProvider.class);


        provider.configure();
    }


    public boolean intercept(MessageContext msgCtx) throws IDStoreNotConfiguredException, InvalidCredentialsException, IOException {


        AuthenticationCredentials authCreds = msgCtx.getAuthCreds();
        if (authCreds == null ) {
            throw new InvalidCredentialsException(MessageService.USERNAME_NULL_EMPTY);
        }
        return provider.authenticate(authCreds);



    }
}
