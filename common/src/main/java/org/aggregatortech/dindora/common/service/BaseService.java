package org.aggregatortech.dindora.common.service;

import org.aggregatortech.dindora.exception.ExceptionService;

import javax.inject.Inject;

public abstract class BaseService {
  @Inject
  ExceptionService exceptionService;

  public ExceptionService getExceptionService() {
    return exceptionService != null ? exceptionService: new ExceptionService();
  }

  public void setExceptionService(ExceptionService exceptionService) {
    this.exceptionService = exceptionService;
  }
}
