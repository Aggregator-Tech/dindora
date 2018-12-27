package org.aggregatortech.dindora.security.authentication.providers

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.aggregatortech.dindora.common.ServiceLocatorHelper
import org.aggregatortech.dindora.common.io.system.SystemHelper
import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exceptions.IDStoreNotConfiguredException
import org.aggregatortech.dindora.exceptions.MessageService
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials

class FileIDProviderTest extends BaseSpecification {


    def "Test throws ID Store Not configured exception if configurations are not valid"() {
        setup:


        when :
          // idstore location is not set
        IDProvider idp = new FileIDProvider();
        then :


        try {
            idp.configure();
            assert false
        }
        catch (IDStoreNotConfiguredException ex) {
            assert  ex instanceof IDStoreNotConfiguredException
        }

        when :
        // idstore location is  empty
       idp = new FileIDProvider();
        SystemHelper helper = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class);
        helper.writeProperty(FileIDProvider.IDSTORE_LOC,"")

        then :


        try {
            idp.configure();
            assert false
        }
        catch (IDStoreNotConfiguredException ex) {
            assert  ex instanceof IDStoreNotConfiguredException
        }

        when :
        // idstore location is  junk
        idp = new FileIDProvider();
        helper = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class);
        helper.writeProperty(FileIDProvider.IDSTORE_LOC,"llllll")

        then :


        try {
            idp.configure();
            assert false
        }
        catch (IDStoreNotConfiguredException ex) {
            assert  ex.getCause() instanceof FileNotFoundException
        }

        when :
        // idstore location is  not a particular format
        idp = new FileIDProvider();
        File f = new File("./idstore");
        f.createNewFile()

        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "atul-welcome1";
        writer.append(jsonStr);
        writer.close();

        helper = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class);
        helper.writeProperty(FileIDProvider.IDSTORE_LOC,"./idstore")

        then :

            try {
                idp.configure();
                assert false
            }
        catch (IDStoreNotConfiguredException ex) {
            assert ex.getCause() instanceof JsonParseException

        }

        when :
        writer = new BufferedWriter(new FileWriter(f));
        jsonStr = "{ \"users\": \"atul\"}";
        writer.append(jsonStr);
        writer.close();

        then :
        try {
            idp.configure();
        }
        catch (IDStoreNotConfiguredException idEx) {
            assert  idEx.getErrorCode().contains(MessageService.IDSTORE_NOT_CONFIGURED)


        }

        when :
        writer = new BufferedWriter(new FileWriter(f));
        jsonStr = "{ \"users\": [{ \"userid\":\"atul\" , \"password\":\"welcome1\" }, " +
          "{ \"userid\":\"ajeet\" , \"password\":\"welcome1\" } ]}";
        writer.append(jsonStr);
        writer.close();

        then :
        try {
            idp.configure();
        }
        catch (IDStoreNotConfiguredException idEx) {
            assert  idEx.getCause() instanceof  UnrecognizedPropertyException


        }
        cleanup:
        f.delete();

    }

    def "Test Configure  is success if format of json is an array of auth creds "() {

        setup:

        when :
        IDProvider idp = new FileIDProvider();
        File f = new File("./idstore");
        f.createNewFile()
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome1\" } ]}";
        writer.append(jsonStr);
        writer.close();

        then :


            idp.configure();


        cleanup:
        f.delete();
    }
    def "Test Authenticate method"() {

        setup:

        when :
        IDProvider idp = new FileIDProvider();
        File f = new File("./idstore");
        f.createNewFile()
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome\" } ]}";
        writer.append(jsonStr);
        writer.close();
        idp.configure();
        then :

        AuthenticationCredentials authCreds = new UserNamePasswordCredentials("atul1",'welcome1');
       assert  idp.authenticate(authCreds) == false


        when :
        authCreds = new UserNamePasswordCredentials("atul",'welcome');
        then:
        assert  idp.authenticate(authCreds) == false


        when :
        authCreds = new UserNamePasswordCredentials("ajeet",'welcome1');
        then:
        assert  idp.authenticate(authCreds) == false


        cleanup:
        f.delete();

    }



    }
