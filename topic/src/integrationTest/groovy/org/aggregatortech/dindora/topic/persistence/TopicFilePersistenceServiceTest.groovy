package org.aggregatortech.dindora.topic.persistence

import com.fasterxml.jackson.databind.ser.Serializers
import org.aggregatortech.dindora.common.service.IdGenerationService
import org.aggregatortech.dindora.persistence.FilePersistenceLocationService
import org.aggregatortech.dindora.topic.object.Topic
import org.glassfish.hk2.api.ServiceLocator
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import platform.common.test.BaseSpecification

class TopicFilePersistenceServiceTest extends BaseSpecification {
    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder();

    def "Test Topic Persistence"() {
        setup:
        ServiceLocator mockServiceLocator = Mock()
        File newFile = temporaryFolder.newFile();
        FilePersistenceLocationService filePersistenceLocationService = Mock()
        mockServiceLocator.getService(FilePersistenceLocationService.class) >> filePersistenceLocationService
        filePersistenceLocationService.getLocation() >> newFile.getAbsolutePath()
        mockServiceLocator.getService(IdGenerationService.class) >> getServiceLocator().getService(IdGenerationService.class)

        TopicFilePersistenceService topicFilePersistenceService = new TopicFilePersistenceService(mockServiceLocator)
        Topic newTopic = new Topic();
        String topicName = "name1";
        String topicDescription = "description1"
        newTopic.setName(topicName)
        newTopic.setDescription(topicDescription)
        Topic retTopic

        when:
        retTopic = topicFilePersistenceService.create(newTopic)

        then:
        retTopic != null
        retTopic.id != null

        when:
        List<Topic> topics = topicFilePersistenceService.search()

        then:
        topics != null
        topics.size() == 1
        Topic topic1 = topics.get(0)
        topic1 != null
        topic1.name == topicName
        topic1.description == topicDescription
    }
}
