package platform.moduleTemplate

import org.aggregatortech.dindora.topic.object.Topic
import org.aggregatortech.dindora.common.test.BaseSpecification

class TopicTest extends BaseSpecification {
    def 'Test Create Topic Object'() {
        setup:
        Topic topic = new Topic();

        when:
        topic.setId("1")
        topic.setName("topicName")
        topic.setDescription("topicDescription")

        then:
        topic.id == "1"
        topic.name == "topicName"
        topic.description == "topicDescription"

        and: "test equals and hashcode method"
        topic.equals(topic)
        !topic.equals(null)
        topic.hashCode() == topic.hashCode()
    }
}
