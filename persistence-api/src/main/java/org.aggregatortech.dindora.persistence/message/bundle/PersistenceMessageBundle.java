package org.aggregatortech.dindora.persistence.message.bundle;

import org.aggregatortech.dindora.message.MessageBundle;
import org.jvnet.hk2.annotations.Service;

@Service(name = "DINDORA_PERSISTENCE")
public class PersistenceMessageBundle implements MessageBundle {
  @Override
  public String getMessage(String code) {
    return PersistenceMessages.valueOf(code).getMessage();
  }

}
