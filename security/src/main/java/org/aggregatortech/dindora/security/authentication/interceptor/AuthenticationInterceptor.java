package org.aggregatortech.dindora.security.authentication.interceptor;


import org.aggregatortech.dindora.exception.InvalidCredentialsException;
import org.aggregatortech.dindora.exception.ProcessingException;
import org.aggregatortech.dindora.security.authentication.providers.IDProvider;
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;
import org.aggregatortech.dindora.security.bundle.SecurityMessages;

import javax.inject.Inject;


public class AuthenticationInterceptor {


    public IDProvider getIdp() {
        return idp;
    }

    private  IDProvider idp = null;

    @Inject
    public AuthenticationInterceptor(IDProvider idp)  {
        if (idp == null ) {
            throw new ProcessingException(SecurityMessages.DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED.toString());
        }
        this.idp = idp;

    }


    public boolean intercept(MessageContext msgCtx) throws  InvalidCredentialsException {


        AuthenticationCredentials authCreds = msgCtx.getAuthCreds();
        if (authCreds == null ) {
            throw new InvalidCredentialsException(SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString());
        }
        return idp.authenticate(authCreds);



    }
}
