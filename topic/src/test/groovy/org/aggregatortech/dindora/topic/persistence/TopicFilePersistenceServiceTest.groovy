package org.aggregatortech.dindora.topic.persistence

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exception.ProcessingException
import org.aggregatortech.dindora.persistence.file.FilePersistenceLocationService
import org.aggregatortech.dindora.topic.object.Topic
import org.glassfish.hk2.api.ServiceLocator
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class TopicFilePersistenceServiceTest extends BaseSpecification {
    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder();

    def "Test Topic Persistence"() {
        setup:
        ServiceLocator mockServiceLocator = Mock()
        File newFile = temporaryFolder.newFile();
        FilePersistenceLocationService mockFilePersistenceLocationService = Mock()
        mockServiceLocator.getService(FilePersistenceLocationService.class) >> mockFilePersistenceLocationService
        mockFilePersistenceLocationService.getLocation() >> newFile.getAbsolutePath()

        TopicFilePersistenceService topicFilePersistenceService = getServiceLocator().getService(TopicFilePersistenceService.class)
        topicFilePersistenceService.setFilePersistenceLocationService(mockFilePersistenceLocationService)
        Topic newTopic1
        newTopic1 = new Topic();
        String topicName = "name";
        String topicDescription = "description"
        newTopic1.setName(topicName + "1")
        newTopic1.setDescription(topicDescription + "1")
        Topic retTopic1

        when: "A new topic is created"
        retTopic1 = topicFilePersistenceService.create(newTopic1)

        then: "It is created successfully"
        retTopic1 != null
        retTopic1.id != null

        when: "All topics are queried"
        List<Topic> topics = topicFilePersistenceService.getAll()

        then: "Recently created topic is returned"
        topics != null
        topics.size() == 1
        topics.get(0) == retTopic1

        when: "Another topic is created"
        Topic newTopic2
        newTopic2 = new Topic();
        newTopic2.setName(topicName + "2")
        newTopic2.setDescription(topicDescription + "2")
        Topic retTopic2
        retTopic2 = topicFilePersistenceService.create(newTopic2)

        then: "It is created successfully"
        retTopic2 != null
        retTopic2.id != null

        when: "All topics are queried"
        topics = topicFilePersistenceService.getAll()

        then: "Both the topics are returned"
        topics != null
        topics.size() == 2
        topics.containsAll(retTopic1, retTopic2)

        when: "A particular topics are queried"
        Topic queryTopic;
        queryTopic = topicFilePersistenceService.getTopic(retTopic2.id)

        then: "It is returned"
        queryTopic != null
        queryTopic == retTopic2

    }

    def "Test Topic Persistence Failure"() {
        setup:
        ServiceLocator mockServiceLocator = Mock()
        File newFile = temporaryFolder.newFile();
        newFile.setReadOnly()
        FilePersistenceLocationService mockFilePersistenceLocationService = Mock()
        mockServiceLocator.getService(FilePersistenceLocationService.class) >> mockFilePersistenceLocationService
        mockFilePersistenceLocationService.getLocation() >> newFile.getAbsolutePath()
        TopicFilePersistenceService topicFilePersistenceService = getServiceLocator().getService(TopicFilePersistenceService.class)
        topicFilePersistenceService.setFilePersistenceLocationService(mockFilePersistenceLocationService)

        when:
        topicFilePersistenceService.create(new Topic())

        then:
        ProcessingException pe = thrown()
        pe.errorMessage.contains("Communication failure with Persistence service.")

        when:
        newFile.delete()
        topicFilePersistenceService.getAll()

        then:
        pe = thrown()
        pe.errorMessage.contains("Communication failure with Persistence service.")
    }
}