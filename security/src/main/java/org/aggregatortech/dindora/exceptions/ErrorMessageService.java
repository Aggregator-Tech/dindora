package org.aggregatortech.dindora.exceptions;

import java.util.HashMap;

public class ErrorMessageService {
    public static final String DINDORA_SECURITY_ERR_CODE_01 = "DINDORA_SECURITY_ERR_CODE_01" ;
    public static final String DINDORA_SECURITY_ERR_CODE_02 = "DINDORA_SECURITY_ERR_CODE_02";
    public static final String DINDORA_SECURITY_ERR_CODE_01_MSG = "User Name is Null or Empty";
    public static final String DINDORA_SECURITY_ERR_CODE_02_MSG = "Password is Null or Empty";
    private static ErrorMessageService errorMessageService= null;
    private static HashMap<String,String> mapError = new HashMap<String,String>();


    public static ErrorMessageService getInstance() {
        if(ErrorMessageService.errorMessageService == null ) {
            errorMessageService =  new ErrorMessageService();

        }

            return errorMessageService;



    }

    private ErrorMessageService () {
        loadErrors();

    }

    private void loadErrors() {

        mapError.put(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01,DINDORA_SECURITY_ERR_CODE_01_MSG);
        mapError.put(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_02,DINDORA_SECURITY_ERR_CODE_02_MSG);

    }

    public String getErrorMessage(String errCode) {
        return mapError.get(errCode);

    }
}
