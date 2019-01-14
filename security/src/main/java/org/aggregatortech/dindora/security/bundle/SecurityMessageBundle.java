package org.aggregatortech.dindora.security.bundle;

import org.aggregatortech.dindora.message.MessageBundle;

import org.jvnet.hk2.annotations.Service;


@Service(name = "DINDORA_SECURITY")
public class SecurityMessageBundle implements MessageBundle {



    @Override
    public String getMessage(String code) {
        return SecurityMessages.valueOf(code).getMessage();
    }

}
