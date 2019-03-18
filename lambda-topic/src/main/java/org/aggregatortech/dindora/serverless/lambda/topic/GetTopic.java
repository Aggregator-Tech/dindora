package org.aggregatortech.dindora.serverless.lambda.topic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.base.Joiner;
import org.aggregatortech.dindora.topic.object.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetTopic extends BaseTopic implements RequestHandler<SearchRequest, SearchResponse> {

  public SearchResponse handleRequest(SearchRequest searchRequest, Context context) {
    initializeContext(context);
    String id = searchRequest.getId();
    getLogService().log("Input: " + id);
    List<Topic> topics = new ArrayList<Topic>();
    if("*".equals(id) || null == id) {
      topics.addAll(getTopicService().getAllTopics());
    } else if(null != id) {
      topics.add(getTopicService().getTopic(id));
    }
    String code = "200";
    getLogService().log("Output: " + Joiner.on("\t").join(topics));
    return new SearchResponse(code, "Topics matched: " + topics.size(), topics);
  }

}