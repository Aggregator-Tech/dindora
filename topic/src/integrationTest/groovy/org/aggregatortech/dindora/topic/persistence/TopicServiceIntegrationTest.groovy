package org.aggregatortech.dindora.topic.persistence

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.persistence.PersistenceService
import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.topic.service.TopicService

class TopicServiceIntegrationTest extends BaseSpecification {

    def "Test Default Persistence Service is File"() {
        setup:
        TopicService topicService = getServiceLocator().getService(TopicService.class)

        PersistenceService<Topic> persistenceService
        when:
        persistenceService = topicService.getPersistenceService()

        then:
        persistenceService instanceof TopicFilePersistenceService

    }

}