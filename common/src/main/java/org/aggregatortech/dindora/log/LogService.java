package org.aggregatortech.dindora.log;

import org.aggregatortech.dindora.exception.BaseException;
import org.aggregatortech.dindora.message.MessageService;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Contract
public interface LogService {

  public void log(String message) ;
}
