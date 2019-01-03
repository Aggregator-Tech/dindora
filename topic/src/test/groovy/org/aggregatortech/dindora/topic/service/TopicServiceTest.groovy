package org.aggregatortech.dindora.topic.service

import org.aggregatortech.dindora.message.bundle.CommonMessages
import org.aggregatortech.dindora.persistence.PersistenceTypeService
import org.aggregatortech.dindora.topic.message.bundle.TopicMessages
import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.persistence.PersistenceService
import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exception.ProcessingException
import org.glassfish.hk2.api.ServiceLocator
import spock.lang.Shared;

import java.util.stream.Collectors
import java.util.stream.Stream

class TopicServiceTest extends BaseSpecification{

    @Shared
    PersistenceTypeService persistenceTypeService = Mock()
    private void sharedMocking(ServiceLocator mockServiceLocator) {
        persistenceTypeService.getPersistenceType() >> PersistenceTypeService.PERSISTENCE_TYPE_FILE
        mockServiceLocator.getService(PersistenceTypeService.class) >> persistenceTypeService

    }

    def "Test get all topics"() {
        setup:
        PersistenceService persistenceService = Mock()
        ServiceLocator mockServiceLocator = Mock()
        mockServiceLocator.getService(PersistenceService.class,_) >> persistenceService
        sharedMocking(mockServiceLocator)
        Topic mockTopic = Mock()
        List<Topic> mockTopics = Stream.of(mockTopic).collect(Collectors.toList())
        TopicService topicService
        topicService = new TopicService(mockServiceLocator)

        when: "There are topics in the repository"
        List<Topic> topics = topicService.getAllTopics()

        then:
        persistenceService.search() >> mockTopics
        topics != null
        topics.size() == 1

        when: "There are no topics in the repository"
        topics = topicService.getAllTopics()

        then:
        persistenceService.search() >> Collections.EMPTY_LIST
        topics != null
        topics.size() == 0

        when: "Unable to process the request"
        topics = topicService.getAllTopics()

        then:
        persistenceService.search() >> {throw new ProcessingException(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())}
        ProcessingException re= thrown()
        re.errorMessage.contains(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.message)

    }

    def "Test persistence service not available"() {
        setup:
        ServiceLocator mockServiceLocator = Mock()
        sharedMocking(mockServiceLocator)
        mockServiceLocator.getService(PersistenceService.class,_) >> null
        TopicService topicService = new TopicService(mockServiceLocator)

        when: "There are more that one topics in the repository"
        List<Topic> topics = topicService.getAllTopics()

        then:
        ProcessingException ex= thrown()
        ex.errorMessage.contains(TopicMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.message)

    }

    def "Test create Topic"() {
        setup:
        TopicService topicService
        PersistenceService mockPersistenceService = Mock()
        ServiceLocator mockServiceLocator = Mock()
        sharedMocking(mockServiceLocator)
        mockServiceLocator.getService(PersistenceService.class,_) >> mockPersistenceService
        topicService = new TopicService(mockServiceLocator)

        String topicId = "id-1"

        Topic mockNewTopic = Mock()

        Topic mockRetTopic = Mock()
        mockRetTopic.id >> topicId
        Topic retTopic;

        when:
        retTopic = topicService.createTopic(mockNewTopic)

        then:
        1 * mockPersistenceService.create(mockNewTopic) >> mockRetTopic
        retTopic != null
        retTopic.id != null
    }


}
