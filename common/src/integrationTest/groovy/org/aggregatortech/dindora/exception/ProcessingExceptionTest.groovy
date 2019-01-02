package org.aggregatortech.dindora.exception

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.message.bundle.CommonMessages

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
