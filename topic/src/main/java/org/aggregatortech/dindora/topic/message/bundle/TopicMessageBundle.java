package org.aggregatortech.dindora.topic.message.bundle;

import org.aggregatortech.dindora.message.MessageBundle;
import org.jvnet.hk2.annotations.Service;

@Service(name = "DINDORA_TOPIC")
public class TopicMessageBundle implements MessageBundle {
  @Override
  public String getMessage(String code) {
    return TopicMessages.valueOf(code).getMessage();
  }

}
