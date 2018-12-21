package org.aggregatortech.dindora.exception.service

import platform.common.test.BaseSpecification

class ErrorMessageServiceTest extends BaseSpecification {
    def "Test GetErrorMessage"() {
        setup:
        ErrorMessageService errorMessageService = serviceLocator.getService(ErrorMessageService.class)

        when:
        String message = errorMessageService.getErrorMessage(CommonErrorMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())

        then:
        message == CommonErrorMessages.DINDORA_COMMON_PROCESSING_FAILED.message
    }
}
