package org.aggregatortech.dindora.exceptions;

import org.jvnet.hk2.annotations.Service;


import java.util.HashMap;


@Service
public class MessageService {
    public static final String USERNAME_NULL_EMPTY = "USERNAME_NULL_EMPTY" ;
    public static final String PASSWORD_NULL_EMPTY = "PASSWORD_NULL_EMPTY";
    public static final String IDSTORE_NOT_CONFIGURED = "Identity Store_NOT_CONFIGURED" ;



    public static Object [][] errMsgs = {

            {USERNAME_NULL_EMPTY, "User Name is Null or Empty"},
            {PASSWORD_NULL_EMPTY,"Password is Null or Empty"},
            {IDSTORE_NOT_CONFIGURED,"ID Store not found or configured" }

    };



    private  HashMap<String,String> mapError = new HashMap<String,String>();

    public MessageService() {

        loadErrors();

    }

    private void loadErrors() {



        for (int i = 0; i < errMsgs.length; ++i) {
            // key must be non-null String, value must be non-null
            String key = (String) errMsgs[i][0];
            Object value = errMsgs[i][1];

            mapError.put(key, (String)value);
        }


    }

    public String getErrorMessage(String errCode) {
        return mapError.get(errCode);

    }



}
