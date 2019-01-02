package org.aggregatortech.dindora.exceptions

import org.aggregatortech.dindora.common.test.BaseSpecification


class ErrorMessageServiceTest  extends BaseSpecification {

    def 'Test ErrorMessage Service is singlelton'() {

        setup:

        ErrorMessageService errMsgService1 = ErrorMessageService.getInstance()
        ErrorMessageService errMsgService2 = ErrorMessageService.getInstance()

        assert errMsgService1 == errMsgService2
    }

    def 'Test ErrorMessage Service returns null if error code does not exist'() {

        setup:

        ErrorMessageService errMsgService = ErrorMessageService.getInstance()


        assert errMsgService.getErrorMessage("12333") == null
    }

    def 'Test ErrorMessage Service returns Valid Error message if error code is valid'() {

        setup:

        ErrorMessageService errMsgService = ErrorMessageService.getInstance()

        assert errMsgService.getErrorMessage(ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01) == ErrorMessageService.DINDORA_SECURITY_ERR_CODE_01_MSG
    }
}
