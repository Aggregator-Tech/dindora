package org.aggregatortech.dindora.exceptions


import org.aggregatortech.dindora.common.test.BaseSpecification


class MessageServiceTest extends BaseSpecification {

    def 'Test ErrorMessage Service is singlelton'() {

        setup:
        MessageService errMsgService1 = serviceLocator.getService(MessageService.class)
        MessageService errMsgService2 = serviceLocator.getService(MessageService.class)

        assert errMsgService1 !=null
        assert errMsgService2 !=null
        assert errMsgService1 == errMsgService2
    }

    def 'Test ErrorMessage Service returns null if error code does not exist'() {
        setup:
        MessageService errMsgService = serviceLocator.getService(MessageService.class)


        assert errMsgService.getErrorMessage("12333") == null
    }

    def 'Test ErrorMessage Service returns Valid Error message if error code is valid'() {

        setup:

        MessageService errMsgService = serviceLocator.getService(MessageService.class)

        assert errMsgService.getErrorMessage(MessageService.USERNAME_NULL_EMPTY) == "User Name is Null or Empty"
    }
}
