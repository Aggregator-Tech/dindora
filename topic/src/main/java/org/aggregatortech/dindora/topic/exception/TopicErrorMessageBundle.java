package org.aggregatortech.dindora.topic.exception;

import org.aggregatortech.dindora.exception.service.CommonErrorMessages;
import org.aggregatortech.dindora.exception.service.ErrorMessageBundle;
import org.jvnet.hk2.annotations.Service;

@Service(name = "DINDORA_TOPIC")
public class TopicErrorMessageBundle implements ErrorMessageBundle {
  @Override
  public String getMessage(String code) {
    return TopicErrorMessages.valueOf(code).getMessage();
  }

}
