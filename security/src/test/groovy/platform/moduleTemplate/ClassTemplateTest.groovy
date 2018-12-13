package platform.moduleTemplate

import org.glassfish.hk2.api.ServiceLocator
import platform.common.CommonConfigProperty
import platform.common.io.system.SystemHelper
import platform.common.test.BaseSpecification

class ClassTemplateTest extends BaseSpecification {
    def 'Test Get Service Description with mock service locator'() {
        setup:
        ServiceLocator mockServiceLocator = Mock()
        SystemHelper mockSystemhelper = Mock()
        mockServiceLocator.getService(SystemHelper.class) >> mockSystemhelper;
        ClassTemplate classTemplate = new ClassTemplate(mockServiceLocator);
        String serviceDescription

        when:
        serviceDescription = classTemplate.getConfigurationProperty(CommonConfigProperty.SERVICE_DESCRIPTION)

        then:
        mockSystemhelper.readMandatoryConfigurationProperty(CommonConfigProperty.SERVICE_DESCRIPTION) >> "testDescription"
        serviceDescription == "testDescription"
    }

    def 'Test Get Service Description with real service locator'() {
        setup:
        ClassTemplate classTemplate = new ClassTemplate();

        when:
        classTemplate.getConfigurationProperty(CommonConfigProperty.SERVICE_DESCRIPTION)

        then:
        RuntimeException ex = thrown()
        ex.message.startsWith("Failed to read configuration property: ")
    }

}
