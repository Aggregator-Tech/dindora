package org.aggregatortech.dindora.security.authentication.providers

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.aggregatortech.dindora.common.io.system.SystemHelper
import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exception.IDStoreNotConfiguredException
import org.aggregatortech.dindora.security.SecurityConfigProperty
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials
import org.aggregatortech.dindora.security.bundle.SecurityMessages
import org.glassfish.hk2.api.ServiceLocator
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class FileIDProviderTest extends BaseSpecification {

    @Rule

    public TemporaryFolder folder= new TemporaryFolder();

    def "Test throws ID Store Not configured exception if idstore file  is  not valid"() {
        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)
        IDStoreNotConfiguredException idEx;
        File f = folder.newFile("idstore");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": \"atul\"}";
        writer.append(jsonStr);
        writer.close();


        when :


        new FileIDProvider(serviceLocator);

        then :
        1* systemHelper.readConfigurationProperty(_,_) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper
        idEx = thrown()
        idEx.getErrorCode() == SecurityMessages.DINDORA_SECURITY_IDSTORE_FORMAT_NOT_VALID.toString()
        f.delete()






    }

    def void 'Test throws unrecognized property if unable to create valid object based on file store'() {
        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)
        //IDProvider idp = null;

        IDStoreNotConfiguredException idEx;
        File f = folder.newFile("idstore");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"userid\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"userid\":\"ajeet\" , \"password\":\"welcome1\" } ]}";
        writer.append(jsonStr);
        writer.close();

        when:


        new FileIDProvider(serviceLocator);

        then:
        1 * systemHelper.readConfigurationProperty(_, _) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper
        idEx = thrown()
        idEx.getCause().class == UnrecognizedPropertyException.class
        f.delete()
    }

    def void 'Test throws ID Store Not configured wraps json parse exception if file does not contain valid json'() {
        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)
        //IDProvider idp = null;

        IDStoreNotConfiguredException idEx;
        File f = folder.newFile("idstore");
        when:
        // idstore location is  not a particular format


        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "atul-welcome1";
        writer.append(jsonStr);
        writer.close();
        new FileIDProvider(serviceLocator);

        then:
        1 * systemHelper.readConfigurationProperty(_, _) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper
        idEx = thrown()
        idEx.getCause().class == JsonParseException.class
        f.delete()
    }

    def void 'Test throws file not found exception wrapped in idstore not configured exceptionif idstore loc is invalid'() {


        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)
        //IDProvider idp = null;

        IDStoreNotConfiguredException idEx;
        File f = folder.newFile("idstore");




        when:
        // idstore location is  junk


        new FileIDProvider(serviceLocator);


        then:
        1 * systemHelper.readConfigurationProperty(_, _) >> new Optional<String>("junk")
        serviceLocator.getService(SystemHelper.class) >> systemHelper
        idEx = thrown()
        idEx.getCause().class == FileNotFoundException.class
    }

    def void 'Test throws ID Store Not configured exception if idstore loc is empty'() {
        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)
        //IDProvider idp = null;

        IDStoreNotConfiguredException idEx;
        File f = folder.newFile("idstore");

        when:

        new FileIDProvider(serviceLocator);

        then:
        1 * systemHelper.readConfigurationProperty(_, _) >> new Optional<String>("")
        1 * serviceLocator.getService(SystemHelper.class) >> systemHelper
        thrown(IDStoreNotConfiguredException.class)
    }

    def void 'Test throws idstore not configured exception if idstore location not set'() {

        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)
        //IDProvider idp = null;

        IDStoreNotConfiguredException idEx;
        File f = folder.newFile("idstore");

        when:
        // idstore location is not set

        new FileIDProvider(serviceLocator);


        then:

        systemHelper.readConfigurationProperty(SecurityConfigProperty.ID_STORE_LOC) >> null
        serviceLocator.getService(SystemHelper.class) >> systemHelper
        thrown(IDStoreNotConfiguredException.class)
    }

    def "Test Configure  is success if format of json is an array of auth creds "() {

        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)

        File   f = folder.newFile("idstore");

        when :

        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome1\" } ]}";
        writer.append(jsonStr);
        writer.close();
        new FileIDProvider(serviceLocator);


        then :
        1* systemHelper.readConfigurationProperty(_,_) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper





    }
    def "Test Authenticate method passes"() {

        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)

        File   f = folder.newFile("idstore");
        def result;
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome\" } ]}";
        writer.append(jsonStr);
        writer.close();
        FileIDProvider idp =  null;
        AuthenticationCredentials authCreds = null;




        when :
        idp = new FileIDProvider(serviceLocator);
        authCreds = new UserNamePasswordCredentials("atul",'welcome1');
        result =  idp.authenticate(authCreds)
        then:
        result == true;
        1* systemHelper.readConfigurationProperty(_,_) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper

        when :
        idp = new FileIDProvider(serviceLocator);
        authCreds = new UserNamePasswordCredentials("ajeet",'welcome');
        result =  idp.authenticate(authCreds)
        then:
        result == true;
        1* systemHelper.readConfigurationProperty(_,_) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper

    }

    def void 'Test Authenticate method fails if password is wrong'() {
        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)

        File   f = folder.newFile("idstore");
        def result;
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome\" } ]}";
        writer.append(jsonStr);
        writer.close();
        FileIDProvider idp =  null;

        when:
        idp = new FileIDProvider(serviceLocator);
        AuthenticationCredentials authCreds = new UserNamePasswordCredentials("atul", 'welcome');
        result = idp.authenticate(authCreds)
        then:
        result == false;
        1 * systemHelper.readConfigurationProperty(_, _) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper



    }

    def void 'Test Authenticate method fails if username is wrong'() {
        setup:
        ServiceLocator serviceLocator = Mock(ServiceLocator.class)
        SystemHelper systemHelper = Mock(SystemHelper.class)

        File   f = folder.newFile("idstore");
        def result;
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String jsonStr = "{ \"users\": [{ \"username\":\"atul\" , \"password\":\"welcome1\" }, " +
                "{ \"username\":\"ajeet\" , \"password\":\"welcome\" } ]}";
        writer.append(jsonStr);
        writer.close();

        when:
        IDProvider idp = new FileIDProvider(serviceLocator);
        AuthenticationCredentials authCreds = new UserNamePasswordCredentials("atul1", 'welcome1');
        result = idp.authenticate(authCreds)
        then:
        result == false;
        1 * systemHelper.readConfigurationProperty(_, _) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper


        when :
        idp = new FileIDProvider(serviceLocator);
        authCreds = new UserNamePasswordCredentials("ajeet",'welcome1');
        result =  idp.authenticate(authCreds)
        then:
        result == false;
        1* systemHelper.readConfigurationProperty(_,_) >> new Optional<String>(f.getAbsolutePath())
        serviceLocator.getService(SystemHelper.class) >> systemHelper
    }


}
