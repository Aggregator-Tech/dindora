package org.aggregatortech.dindora.serverless.lambda.topic;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import org.aggregatortech.dindora.serverless.lambda.topic.interceptor.annotation.RequiresLoggingConfiguration;
import org.aggregatortech.dindora.topic.object.Topic;
import org.glassfish.hk2.extras.interception.Intercepted;
import org.jvnet.hk2.annotations.Service;

@Service
@Intercepted
public class CreateTopic extends BaseTopic {

  @RequiresLoggingConfiguration
  public Response handleRequest(Topic topic, Context context) {
    getLogService().log("Input: " + topic.getName() + " " + topic.getDescription());
    Topic newTopic = getTopicService().createTopic(topic);
    String code = "201";
    getLogService().log("Output: " + newTopic.getId());
    return new Response(code, "Topic created with id: " + newTopic.getId());
  }
}