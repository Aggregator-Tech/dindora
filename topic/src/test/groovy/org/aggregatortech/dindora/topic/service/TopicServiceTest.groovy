package org.aggregatortech.dindora.topic.service

import org.aggregatortech.dindora.message.bundle.CommonMessages
import org.aggregatortech.dindora.topic.message.bundle.TopicMessages
import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.persistence.PersistenceService
import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.exception.ProcessingException
import java.util.stream.Collectors
import java.util.stream.Stream

class TopicServiceTest extends BaseSpecification{

    def "Test get all topics"() {
        setup:
        PersistenceService persistenceService = Mock()
        Topic mockTopic = Mock()
        List<Topic> mockTopics = Stream.of(mockTopic).collect(Collectors.toList())
        TopicService topicService
        topicService = new TopicService()
        topicService.setPersistenceService(persistenceService)

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
        topicService.getAllTopics()

        then:
        persistenceService.search() >> {throw new ProcessingException(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())}
        ProcessingException re= thrown()
        re.errorCode == CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString()

    }

    def "Test persistence service not available"() {
        setup:
        TopicService topicService = new TopicService()

        when: "There are more that one topics in the repository"
        topicService.getAllTopics()

        then:
        ProcessingException ex = thrown()
        ex.errorCode == TopicMessages.DINDORA_TOPIC_PERSISTENCE_MISSING.toString()
    }

    def "Test create Topic"() {
        setup:
        PersistenceService mockPersistenceService = Mock()
        TopicService topicService
        topicService = new TopicService()
        topicService.setPersistenceService(mockPersistenceService)

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
