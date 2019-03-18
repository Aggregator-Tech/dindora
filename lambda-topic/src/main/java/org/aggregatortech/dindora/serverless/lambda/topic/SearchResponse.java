package org.aggregatortech.dindora.serverless.lambda.topic;

import org.aggregatortech.dindora.topic.object.Topic;

import java.util.List;

public class SearchResponse extends Response{
    public SearchResponse(List<Topic> topics) {
        this.topics = topics;
    }

    public SearchResponse(String code, String message, List<Topic> topics) {
        super(code, message);
        this.topics = topics;
    }

    List<Topic> topics;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}