package org.aggregatortech.dindora.message.bundle;

import org.aggregatortech.dindora.message.MessageBundle;
import org.jvnet.hk2.annotations.Service;

@Service(name = "DINDORA_COMMON")
public class CommonMessageBundle implements MessageBundle {
  @Override
  public String getMessage(String code) {
    return CommonMessages.valueOf(code).getMessage();
  }

}
