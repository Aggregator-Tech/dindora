package org.aggregatortech.dindora.security.authentication.providers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aggregatortech.dindora.common.ServiceLocatorHelper;
import org.aggregatortech.dindora.common.io.system.SystemHelper;
import org.aggregatortech.dindora.exceptions.IDStoreNotConfiguredException;
import org.aggregatortech.dindora.exceptions.InvalidCredentialsException;
import org.aggregatortech.dindora.exceptions.MessageService;
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials;
import org.jvnet.hk2.annotations.Service;


import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileIDProvider implements IDProvider {


    public static final String IDSTORE_LOC = "idstore.loc";
    //private JsonNode jsonNode;
    private AuthenticationCredentials[] authCreds;


    private File fileLoc = null;

    @Override
    public void configure() throws IDStoreNotConfiguredException {
        //File fileLoc = null;
        SystemHelper systemHelper = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class);
        String propName = IDSTORE_LOC;
        Optional<String> propertyValue = systemHelper.readConfigurationProperty(propName, null);


        if (!propertyValue.isPresent() || propertyValue.get().trim().isEmpty()) {
            throw new IDStoreNotConfiguredException(MessageService.IDSTORE_NOT_CONFIGURED);
        }


        JsonFactory factory = new JsonFactory();
        JsonNode jsonNode;
        try {
            //JsonParser parser =  factory.createParser(fileLoc);
            File fileLoc = new File(propertyValue.get());
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(fileLoc);

        } catch (Exception ex) {

            throw new IDStoreNotConfiguredException(MessageService.IDSTORE_NOT_CONFIGURED, ex);
        }


        //parse file and see if it follows a particular format

        JsonNode jsonNodeUsers = jsonNode.get("users");
        String str = null;
        if (!jsonNodeUsers.isArray()) {

            throw new IDStoreNotConfiguredException(MessageService.IDSTORE_NOT_CONFIGURED);
        }



        ObjectMapper mapper = new ObjectMapper();


        try {

           authCreds = mapper.readValue(jsonNodeUsers.toString(),
                    UserNamePasswordCredentials[].class);
        }
        catch (Exception ex ){
            throw new IDStoreNotConfiguredException(MessageService.IDSTORE_NOT_CONFIGURED,ex);
        }


}
    @Override
    public boolean authenticate(AuthenticationCredentials authCredsIn) throws InvalidCredentialsException, IOException {

        boolean isSuccess = false;
        for (AuthenticationCredentials authCredsElement: authCreds
             ) {
               if ( authCredsElement.equals(authCredsIn) )
            {
                isSuccess = true;
               return true;
            }
            
        }

        return false;


    }

  




}
