package org.aggregatortech.dindora.message

import org.aggregatortech.dindora.message.bundle.CommonMessages
import platform.common.test.BaseSpecification

class MessageServiceTest extends BaseSpecification {
    def "Test GetMessage"() {
        setup:
        MessageService messageService = serviceLocator.getService(MessageService.class)

        when:
        String message = messageService.getMessage(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())

        then:
        message == CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.message
    }
}
