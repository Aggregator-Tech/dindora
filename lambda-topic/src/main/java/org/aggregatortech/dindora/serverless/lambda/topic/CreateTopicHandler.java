package org.aggregatortech.dindora.serverless.lambda.topic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.aggregatortech.dindora.common.ServiceLocatorHelper;
import org.aggregatortech.dindora.topic.object.Topic;

public class CreateTopicHandler extends BaseTopic implements RequestHandler<Topic, Response> {

  public Response handleRequest(Topic topic, Context context) {
    return ServiceLocatorHelper.getServiceLocator()
        .getService(CreateTopic.class)
        .handleRequest(topic, context);
  }
}