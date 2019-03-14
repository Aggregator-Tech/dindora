package org.aggregatortech.dindora.topic.persistence

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.persistence.dynamodb.DynamoDbHelper
import org.aggregatortech.dindora.topic.object.Topic
import spock.lang.Requires

class TopicDynamoDbPersistenceServiceTest extends BaseSpecification {
    @Requires({TopicDynamoDbPersistenceServiceTest.shouldRunTest()})
    def "Test Topic Persistence"() {
        setup:

        TopicDynamoDbPersistenceService topicDynamoDBPersistenceService = getServiceLocator().getService(TopicDynamoDbPersistenceService.class)
        Topic newTopic1
        newTopic1 = new Topic();
        String topicName = "name";
        String topicDescription = "description"
        newTopic1.setName(topicName + "2")
        newTopic1.setDescription(topicDescription + "2")
        Topic retTopic1

        when: "A new topic is created"
        retTopic1 = topicDynamoDBPersistenceService.create(newTopic1)

        then: "It is created successfully"
        retTopic1 != null
        retTopic1.id != null

        when: "All topics are queried"
        List<Topic> topics = topicDynamoDBPersistenceService.getAll()

        then: "Recently created topic is returned"
        topics != null
        topics.contains(retTopic1)

    }

    static boolean shouldRunTest() {
        boolean shouldRun = getServiceLocator().getService(DynamoDbHelper.class).checkConfiguration()
        return shouldRun
    }
}