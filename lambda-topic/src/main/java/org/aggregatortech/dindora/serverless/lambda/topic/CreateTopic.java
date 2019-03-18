package org.aggregatortech.dindora.serverless.lambda.topic;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import org.aggregatortech.dindora.topic.object.Topic;

public class CreateTopic extends BaseTopic implements RequestHandler<Topic, Response> {

  public Response handleRequest(Topic topic, Context context) {
    getLogService().log("Input: " + topic.getName() + " " + topic.getDescription());
    Topic newTopic = getTopicService().createTopic(topic);
    String code = "201";
    getLogService().log("Output: " + newTopic.getId());
    return new Response(code, "Topic created with id: " + newTopic.getId());
  }
}