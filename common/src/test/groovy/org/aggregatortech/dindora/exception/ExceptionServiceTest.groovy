package org.aggregatortech.dindora.exception

import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.message.bundle.CommonMessages

class ExceptionServiceTest extends BaseSpecification {
    def "Test build exception without message service"() {
        ExceptionService exceptionService = new ExceptionService();

        when:
        ProcessingException pe =
                exceptionService.buildException(new ProcessingException(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString()))
        then:
        pe.errorCode == CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString()
        pe.errorMessage == null
    }

    def "Test build exception"() {
        ExceptionService exceptionService = getServiceLocator().getService(ExceptionService.class);

        when:
        ProcessingException pe =
                exceptionService.buildException(new ProcessingException(CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString()))
        then:
        pe.errorCode == CommonMessages.DINDORA_COMMON_PROCESSING_FAILED.toString()
        pe.errorMessage.contains("Unable to process the request.")
    }
}
