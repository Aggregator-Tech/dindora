package org.aggregatortech.dindora.exception.service;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ErrorMessageBundle {
  String getMessage(String code);
}
