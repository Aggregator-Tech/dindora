package org.aggregatortech.dindora.message

import spock.lang.Specification

class MessageBundleNameServiceTest extends Specification{
    def "Test get message bundle name"() {
        setup:
        MessageBundleNameService messageBundleNameService = new MessageBundleNameService()

        when:
        String messageBundleName = messageBundleNameService.getMessageBundleName("DINDORA_SECURITY_ERR_CODE_01")

        then:
        messageBundleName == "DINDORA_SECURITY"
    }

    def "Test get message bundle name with invalid name"() {
        setup:
        MessageBundleNameService messageBundleNameService = new MessageBundleNameService()

        when:
        String messageBundleName = messageBundleNameService.getMessageBundleName("XXX")

        then:
        messageBundleName == null
    }

    def "Test get message bundle name with null error code"() {
        setup:
        MessageBundleNameService messageBundleNameService = new MessageBundleNameService()

        when:
        String messageBundleName = messageBundleNameService.getMessageBundleName(null)

        then:
        messageBundleName == null
    }
}
