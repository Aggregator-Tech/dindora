package org.aggregatortech.dindora.topic.interceptor;

import org.aggregatortech.dindora.topic.interceptor.annotation.RequiresAuthorization;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.glassfish.hk2.extras.interception.Interceptor;
import org.jvnet.hk2.annotations.ContractsProvided;
import org.jvnet.hk2.annotations.Service;

@Service
@Interceptor
@ContractsProvided({MethodInterceptor.class})
@RequiresAuthorization
public class AuthorizationInterceptor implements MethodInterceptor{
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Object args[] = invocation.getArguments();
    String id = (String) args[0];
    if("unauthorized-id".equals(id)) {
      throw new RuntimeException("Not Authorized");
    }
    return invocation.proceed();
  }
}
