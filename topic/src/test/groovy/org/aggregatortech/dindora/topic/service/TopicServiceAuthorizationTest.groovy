package org.aggregatortech.dindora.topic.service

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.topic.persistence.TopicPersistenceService
import java.util.stream.Collectors
import java.util.stream.Stream

class TopicServiceAuthorizationTest extends BaseSpecification {

    def "Test Topic Service Authorization"() {
        TopicService topicService = getServiceLocator().getService(TopicService.class);
        TopicPersistenceService mockPersistenceService = Mock()
        Topic mockTopic = Mock()
        topicService.setPersistenceService(mockPersistenceService)

        when:
        topicService.getTopic("unauthorized-id")

        then:
        Exception e= thrown()
        println "e = $e"

        when:
        Topic queryTopic;
        queryTopic = topicService.getTopic("authorized-id")

        then:
        1 * mockPersistenceService.get("authorized-id") >> mockTopic
        queryTopic == mockTopic


    }

}
