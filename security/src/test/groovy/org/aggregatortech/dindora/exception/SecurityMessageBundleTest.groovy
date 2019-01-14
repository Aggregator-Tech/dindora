package org.aggregatortech.dindora.exception


import org.aggregatortech.dindora.common.test.BaseSpecification
import org.aggregatortech.dindora.message.MessageService
import org.aggregatortech.dindora.security.bundle.SecurityMessages


class SecurityMessageBundleTest extends BaseSpecification {


    def 'Test ErrorMessage Service returns null if error code does not exist'() {
        setup:
        MessageService errMsgService = serviceLocator.getService(MessageService.class)

        when :
        errMsgService.getMessage("12333")
        then:
        thrown(IllegalArgumentException.class )
    }

    def 'Test ErrorMessage Service returns Valid Error message if error code is valid'() {

        setup:

        MessageService errMsgService = serviceLocator.getService(MessageService.class)

        assert errMsgService.getMessage(SecurityMessages.DINDORA_SECURITY_USERNAME_NULL_EMPTY.toString()) == "User Name is Null or Empty"
    }
}
