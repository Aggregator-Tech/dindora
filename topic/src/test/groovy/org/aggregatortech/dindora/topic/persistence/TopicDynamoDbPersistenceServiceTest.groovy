package org.aggregatortech.dindora.topic.persistence

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.topic.object.Topic
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class TopicDynamoDbPersistenceServiceTest extends BaseSpecification {
    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder();

    def "Test Topic Persistence"() {
        setup:

        TopicDynamoDbPersistenceService topicDynamoDBPersistenceService = getServiceLocator().getService(TopicDynamoDbPersistenceService.class)
        Topic newTopic1
        newTopic1 = new Topic();
        String topicName = "name";
        String topicDescription = "description"
        newTopic1.setId("aaa-111")
        newTopic1.setName(topicName + "1")
        newTopic1.setDescription(topicDescription + "1")
        Topic retTopic1

        when: "A new topic is created"
        retTopic1 = topicDynamoDBPersistenceService.create(newTopic1)

        then: "It is created successfully"
        retTopic1 != null
        retTopic1.id != null

        when: "All topics are queried"
        List<Topic> topics = topicDynamoDBPersistenceService.search()

        then: "Recently created topic is returned"
        topics != null
        topics.contains(retTopic1)

    }


}