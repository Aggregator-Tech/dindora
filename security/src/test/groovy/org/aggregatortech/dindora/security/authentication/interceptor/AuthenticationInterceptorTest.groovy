package org.aggregatortech.dindora.security.authentication.interceptor


import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exception.IDStoreNotConfiguredException
import org.aggregatortech.dindora.exception.InvalidCredentialsException

import org.aggregatortech.dindora.security.authentication.providers.IDProvider
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials
import org.aggregatortech.dindora.security.bundle.SecurityMessages

class AuthenticationInterceptorTest extends BaseSpecification{



    def "Test initialization fails if configure method throws Exception"() {

        setup:
        IDProvider idp = null


        when:

            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(idp)

        then:
            idp.configure(serviceLocator) >> { throw new IDStoreNotConfiguredException(SecurityMessages.DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED.toString()) }
            RuntimeException ex = thrown()
            ex.getMessage() == SecurityMessages.DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED.toString()




    }

    def "Test intercept request and fail for the lack of/invalid credentials"() {

        setup:
        IDProvider idp = Mock(IDProvider.class)
        String password = "welcome2";
        String username = "atul";

        AuthenticationCredentials creds = new UserNamePasswordCredentials(username,password);
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(idp);

        MessageContext msgCtx =  new MessageContext();

        when :

            msgCtx.setAuthCreds(null);


        then:
        try {
              interceptor.intercept(msgCtx);
            assert false;
        }
        catch(InvalidCredentialsException ex) {
            assert ex.getErrorCode() == SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString();
        }



        msgCtx.setAuthCreds(creds);
        def intercept
        when :

        intercept = interceptor.intercept(msgCtx)

        then:
            idp.authenticate(creds) >> false





    }

    def "Test intercept request and pass with valid credentials"() {
        setup:
        IDProvider idp = Mock(IDProvider.class)
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(idp);

        MessageContext msgCtx =  new MessageContext();

        AuthenticationCredentials creds = null;
        String password = "welcome1";
        String username = "atul";
        creds = new UserNamePasswordCredentials(username,password);
        def intercept
        when :
        msgCtx.setAuthCreds(creds);
        intercept = interceptor.intercept(msgCtx)

        then:
        1* idp.authenticate(creds) >> true
        assert intercept;





    }

    }
