package org.aggregatortech.dindora.message

import org.aggregatortech.dindora.message.bundle.CommonMessageBundle
import org.aggregatortech.dindora.message.bundle.CommonMessages
import spock.lang.Specification

class CommonMessageBundleTest extends Specification {
    def "Test GetMessage"() {
        setup:
        CommonMessageBundle commonMessageBundle = new CommonMessageBundle()
        String message;
        when:
        message = commonMessageBundle.getMessage(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())
        then:
        message == CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.message
    }
}
