package org.aggregatortech.dindora.topic.service

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.persistence.PersistenceTypeService
import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.topic.persistence.TopicDynamoDbPersistenceService
import static org.aggregatortech.dindora.persistence.PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB
import spock.lang.Requires

class TopicServiceWithDynamoDbTest extends BaseSpecification {
    static boolean shouldRunTest() {
        PersistenceTypeService persistenceTypeService = getServiceLocator().getService(PersistenceTypeService.class)
        return (persistenceTypeService.getPersistenceType() == PERSISTENCE_TYPE_DYNAMO_DB);
    }
    @Requires({TopicServiceWithDynamoDbTest.shouldRunTest()})
    def "Test createTopic"() {
        TopicService topicService = getServiceLocator().getService(TopicService.class);
        expect:
        topicService.getPersistenceService() instanceof TopicDynamoDbPersistenceService

        when: "A new topic is created"
        Topic newTopic1
        newTopic1 = new Topic();
        String topicName = "name";
        String topicDescription = "description"
        newTopic1.setName(topicName + "3")
        newTopic1.setDescription(topicDescription + "3")
        Topic retTopic1
        retTopic1 = topicService.createTopic(newTopic1)

        then: "It is created successfully"
        retTopic1 != null
        retTopic1.id != null

        when: "All topics are queried"
        List<Topic> topics = topicService.getAllTopics()

        then: "Recently created topic is returned"
        topics != null
        topics.contains(retTopic1)   }
}
