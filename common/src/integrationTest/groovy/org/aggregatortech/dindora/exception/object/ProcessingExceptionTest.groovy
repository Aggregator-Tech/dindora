package org.aggregatortech.dindora.exception.object

import org.aggregatortech.dindora.message.bundle.CommonMessages
import platform.common.test.BaseSpecification

class ProcessingExceptionTest extends BaseSpecification{
    def "Test ProcessingException"() {
        setup:
        ProcessingException processingException =
                new ProcessingException(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString())

        when:
        String message = processingException.getErrorMessage()

        then:
        message == "DINDORA_COMMON_PROCESSING_FAILED  :  Unable to process the request."
    }
}
