package org.aggregatortech.dindora.serverless.lambda.topic.interceptor;

import org.aggregatortech.dindora.serverless.lambda.LambdaLogService;
import org.aggregatortech.dindora.serverless.lambda.topic.interceptor.annotation.RequiresLoggingConfiguration;
import org.aggregatortech.dindora.topic.interceptor.annotation.RequiresAuthorization;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.glassfish.hk2.extras.interception.Interceptor;
import org.jvnet.hk2.annotations.ContractsProvided;
import org.jvnet.hk2.annotations.Service;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;

@Service
@Interceptor
@ContractsProvided({MethodInterceptor.class})
@RequiresLoggingConfiguration
public class LoggingConfigurationInterceptor implements MethodInterceptor{

  @Inject
  LambdaLogService lambdaLogService;

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Object args[] = invocation.getArguments();
    Context context = (Context) args[1];
    if(null != lambdaLogService) {
      lambdaLogService.setLambdaLogger(context.getLogger());
    }
    return invocation.proceed();
  }
}
