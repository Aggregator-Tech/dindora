package org.aggregatortech.dindora.security.authentication.interceptor

import org.aggregatortech.dindora.common.ServiceLocatorHelper
import org.aggregatortech.dindora.common.io.system.SystemHelper;
import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exceptions.InvalidCredentialsException
import org.aggregatortech.dindora.exceptions.MessageService
import org.aggregatortech.dindora.security.authentication.providers.FileIDProvider
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials

class AuthenticationInterceptorTest extends BaseSpecification{


    def "Test intercept request and fail for the lack of/invalid credentials"() {

        setup:
        SystemHelper helper = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class);
        helper.writeProperty(FileIDProvider.IDSTORE_LOC,"./idstore")
        File f = new File("./idstore");
        f.createNewFile()
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome1\" } ]}";
        writer.append(jsonStr);
        writer.close();
        MessageContext msgCtx =  new MessageContext();
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor();
        when :
            AuthenticationCredentials creds = null;
            msgCtx.setAuthCreds(creds);

        then:
        try {
            boolean isSuccess = interceptor.intercept(msgCtx);
            assert false;
        }
        catch(InvalidCredentialsException ex) {
            assert ex.getErrorCode() == MessageService.USERNAME_NULL_EMPTY;
        }

        when :

            String password = "welcome2";
            String username = "atul";
            creds = new UserNamePasswordCredentials(username,password);
            msgCtx.setAuthCreds(creds);
            then:
                assert interceptor.intercept(msgCtx) == false;

        cleanup:
        f.delete();



    }

    def "Test intercept request and pass with valid credentials"() {
        setup:
        SystemHelper helper = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class);
        helper.writeProperty(FileIDProvider.IDSTORE_LOC,"./idstore")
        File f = new File("./idstore");
        f.createNewFile()
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome1\" } ]}";
        writer.append(jsonStr);
        writer.close();
        MessageContext msgCtx =  new MessageContext();
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor();
        AuthenticationCredentials creds = null;
        when :
        String password = "welcome1";
        String username = "atul";
        creds = new UserNamePasswordCredentials(username,password);
        msgCtx.setAuthCreds(creds);

        msgCtx.setAuthCreds(creds);

        then:

           assert interceptor.intercept(msgCtx) == true;

        when :
        password = "welcome1";
        username = "ajeet";
        creds = new UserNamePasswordCredentials(username,password);
        msgCtx.setAuthCreds(creds);

        msgCtx.setAuthCreds(creds);
        then :
        assert interceptor.intercept(msgCtx) == true;


        cleanup:
                f.delete();

    }

    }
