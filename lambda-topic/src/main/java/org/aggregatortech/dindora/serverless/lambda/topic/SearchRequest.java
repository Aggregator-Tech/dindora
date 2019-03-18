package org.aggregatortech.dindora.serverless.lambda.topic;

public class SearchRequest {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SearchRequest(String id) {
        this.id = id;
    }

    public SearchRequest() {
    }
}