package org.aggregatortech.dindora.message;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface MessageBundle {
  String getMessage(String code);
}
