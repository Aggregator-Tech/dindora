package org.aggregatortech.dindora.web.service

import org.aggregatortech.dindora.common.CommonConfigProperty
import org.aggregatortech.dindora.common.ServiceLocatorHelper
import org.aggregatortech.dindora.common.io.system.SystemHelper
import spock.lang.Specification

class ServerTest extends Specification {
    def "GetBaseUri"() {
        when:
        Server server = new Server();
        server.getBaseUrl()
        then:
        RuntimeException runtimeException = thrown()
        runtimeException.getMessage() == "Failed to read configuration property: servicePort"
    }

    def "GetBaseUri with configured port"() {
        when:
        Server server = new Server();
        ServiceLocatorHelper.serviceLocator.getService(SystemHelper.class)
                .writeConfigurationProperty(CommonConfigProperty.SERVICE_PORT, "9500")
        then:
        assert server.getBaseUrl() == "http://0.0.0.0:9500/"
    }

}
