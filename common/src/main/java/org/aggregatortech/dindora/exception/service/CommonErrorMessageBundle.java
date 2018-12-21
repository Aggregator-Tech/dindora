package org.aggregatortech.dindora.exception.service;

import org.jvnet.hk2.annotations.Service;

@Service(name = "DINDORA_COMMON")
public class CommonErrorMessageBundle implements ErrorMessageBundle {
  @Override
  public String getMessage(String code) {
    return CommonErrorMessages.valueOf(code).getMessage();
  }

}
