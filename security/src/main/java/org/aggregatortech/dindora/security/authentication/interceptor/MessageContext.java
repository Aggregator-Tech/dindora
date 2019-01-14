package org.aggregatortech.dindora.security.authentication.interceptor;

import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;

public class MessageContext {
    AuthenticationCredentials authCreds = null;

    public AuthenticationCredentials getAuthCreds() {
        return authCreds;
    }

    public void setAuthCreds(AuthenticationCredentials authCreds) {
        this.authCreds = authCreds;
    }




}
