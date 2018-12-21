package org.aggregatortech.dindora.exception.service

import spock.lang.Specification

class CommonErrorMessageBundleTest extends Specification {
    def "Test GetMessage"() {
        setup:
        CommonErrorMessageBundle commonErrorMessageBundle = new CommonErrorMessageBundle()
        String message;
        when:
        message = commonErrorMessageBundle.getMessage(CommonErrorMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())
        then:
        message == CommonErrorMessages.DINDORA_COMMON_PROCESSING_FAILED.message
    }
}
