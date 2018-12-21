package org.aggregatortech.dindora.exception.service

import spock.lang.Specification

class ErrorBundleNameServiceTest extends Specification{
    def "Test get error bundle name"() {
        setup:
        ErrorBundleNameService errorBundleNameService = new ErrorBundleNameService()

        when:
        String errorBundleName = errorBundleNameService.getErrorBundleName("DINDORA_SECURITY_ERR_CODE_01")

        then:
        errorBundleName == "DINDORA_SECURITY"
    }

    def "Test get error bundle name with invalid name"() {
        setup:
        ErrorBundleNameService errorBundleNameService = new ErrorBundleNameService()

        when:
        String errorBundleName = errorBundleNameService.getErrorBundleName("XXX")

        then:
        errorBundleName == null
    }
}
