package org.aggregatortech.dindora.serverless.lambda;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.aggregatortech.dindora.log.LogService;
import org.jvnet.hk2.annotations.Service;

@Service
public class LambdaLogService implements LogService {

  public void setLambdaLogger(LambdaLogger lambdaLogger) {
    this.lambdaLogger = lambdaLogger;
  }

  private LambdaLogger lambdaLogger;

  @Override
  public void log(String message) {
    lambdaLogger.log(message);
  }
}
