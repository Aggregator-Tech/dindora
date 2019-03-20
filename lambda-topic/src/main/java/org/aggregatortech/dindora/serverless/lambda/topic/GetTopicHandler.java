package org.aggregatortech.dindora.serverless.lambda.topic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.base.Joiner;
import org.aggregatortech.dindora.common.ServiceLocatorHelper;
import org.aggregatortech.dindora.topic.object.Topic;

import java.util.ArrayList;
import java.util.List;

public class GetTopicHandler extends BaseTopic implements RequestHandler<SearchRequest, SearchResponse> {

  public SearchResponse handleRequest(SearchRequest searchRequest, Context context) {
    return ServiceLocatorHelper.getServiceLocator()
        .getService(GetTopic.class)
        .handleRequest(searchRequest, context);
    }

}