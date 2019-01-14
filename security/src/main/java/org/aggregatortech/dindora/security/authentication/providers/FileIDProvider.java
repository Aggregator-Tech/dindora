package org.aggregatortech.dindora.security.authentication.providers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aggregatortech.dindora.common.io.system.SystemHelper;
import org.aggregatortech.dindora.exception.IDStoreNotConfiguredException;
import org.aggregatortech.dindora.security.authentication.token.AuthenticationCredentials;
import org.aggregatortech.dindora.security.authentication.token.UserNamePasswordCredentials;
import org.aggregatortech.dindora.security.bundle.SecurityMessages;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;


import javax.inject.Inject;
import java.io.File;
import java.util.Optional;

@Service
public class FileIDProvider implements IDProvider {


    public static final String ID_STORE_LOC = "id-store.loc";


    private AuthenticationCredentials[] credentials;

    @Inject
    public FileIDProvider(ServiceLocator serviceLocator) throws IDStoreNotConfiguredException {
        super();
        configure(serviceLocator);
    }





    protected void configure(ServiceLocator serviceLocator) throws IDStoreNotConfiguredException {
        //File fileLoc = null;

        SystemHelper systemHelper = serviceLocator.getService(SystemHelper.class);
        Optional<String> propertyValue = systemHelper.readConfigurationProperty(ID_STORE_LOC,null);


        if (propertyValue == null || !propertyValue.isPresent() || propertyValue.get().trim().isEmpty()) {
            throw new IDStoreNotConfiguredException(SecurityMessages.DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED.toString());
        }



        JsonNode jsonNode;
        try {

            File fileLoc = new File(propertyValue.get());
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(fileLoc);

        } catch (Exception ex) {

            throw new IDStoreNotConfiguredException(SecurityMessages.DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED.toString(), ex);
        }


        //parse file and see if it follows a particular format

        JsonNode jsonNodeUsers = jsonNode.get("users");

        if (!jsonNodeUsers.isArray()) {

            throw new IDStoreNotConfiguredException(SecurityMessages.DINDORA_SECURITY_IDSTORE_FORMAT_NOT_VALID.toString());
        }

        ObjectMapper mapper = new ObjectMapper();


        try {

           credentials = mapper.readValue(jsonNodeUsers.toString(),
                    UserNamePasswordCredentials[].class);
        }
        catch (Exception ex ){
            throw new IDStoreNotConfiguredException(SecurityMessages.DINDORA_SECURITY_IDSTORE_NOT_CONFIGURED.toString(),ex);
        }


}
    @Override
    public boolean authenticate(AuthenticationCredentials credentialsIn){



        for (AuthenticationCredentials credentialsItem: credentials
             ) {
               if ( credentialsItem.equals(credentialsIn) )
            {
               
               return true;
            }
            
        }

        return false;


    }

  




}
