package org.aggregatortech.dindora.serverless.lambda.topic;

import com.amazonaws.services.lambda.runtime.Context;
import org.aggregatortech.dindora.common.ServiceLocatorHelper;
import org.aggregatortech.dindora.log.LogService;
import org.aggregatortech.dindora.serverless.lambda.LambdaLogService;
import org.aggregatortech.dindora.topic.service.TopicService;

public abstract class BaseTopic {

  public TopicService getTopicService() {

    if( null == topicService ) {
      topicService = ServiceLocatorHelper.getServiceLocator().getService(TopicService.class);
    }
    return topicService;
  }

  public void setTopicService(TopicService topicService) {
    this.topicService = topicService;
  }

  TopicService topicService;

  public LogService getLogService() {
    if( null == logService ) {
      logService = ServiceLocatorHelper.getServiceLocator().getService(LogService.class);
    }return logService;
  }

  LogService logService;

  protected void initializeContext(Context context) {
    if(getLogService() instanceof LambdaLogService) {
      ((LambdaLogService)getLogService()).setLambdaLogger(context.getLogger());
    }
  }
}