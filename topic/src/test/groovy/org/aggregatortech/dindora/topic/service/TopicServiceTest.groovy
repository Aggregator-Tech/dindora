package org.aggregatortech.dindora.topic.service

import org.aggregatortech.dindora.exception.service.CommonErrorMessages
import org.aggregatortech.dindora.topic.exception.TopicErrorMessages
import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.persistence.PersistenceService
import platform.common.test.BaseSpecification
import org.aggregatortech.dindora.exception.object.ProcessingException;

import java.util.stream.Collectors
import java.util.stream.Stream

class TopicServiceTest extends BaseSpecification{

    def "Test get all topics"() {
        setup:
        PersistenceService persistenceService = Mock()
        Topic mockTopic = Mock()
        List<Topic> mockTopics = Stream.of(mockTopic).collect(Collectors.toList())
        TopicService topicService
        topicService = new TopicService(persistenceService)

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
        persistenceService.search() >> {throw new ProcessingException(CommonErrorMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())}
        ProcessingException re= thrown()
        re.errorMessage.contains(CommonErrorMessages.DINDORA_COMMON_PROCESSING_FAILED.message)

    }

    def "Test persistence service not available"() {
        setup:
        TopicService topicService = new TopicService(null)

        when: "There are more that one topics in the repository"
        List<Topic> topics = topicService.getAllTopics()

        then:
        ProcessingException ex= thrown()
        ex.errorMessage.contains(TopicErrorMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.message)

    }

    def "Test create Topic"() {
        setup:
        TopicService topicService
        PersistenceService mockPersistenceService = Mock()
        topicService = new TopicService(mockPersistenceService)

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
