package org.aggregatortech.dindora.persistence

import org.aggregatortech.dindora.common.io.system.SystemHelper
import org.glassfish.hk2.api.ServiceLocator
import spock.lang.Specification

class PersistenceTypeServiceTest extends Specification {
    def "Get PersistenceType"() {
        ServiceLocator mockServiceLocator = Mock()
        SystemHelper mockSystemHelper = Mock()
        mockServiceLocator.getService(SystemHelper.class) >> mockSystemHelper
        mockSystemHelper.readConfigurationProperty(PersistenceConfigProperty.PERSISTENCE_TYPE) >> Optional.of(PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB)
        PersistenceTypeService persistenceTypeService =
                new PersistenceTypeService(mockServiceLocator)
        String persistenceType
        when:
        persistenceType = persistenceTypeService.getPersistenceType()
        then:
        persistenceType == PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB
    }

    def "Get default PersistenceType"() {
        ServiceLocator mockServiceLocator = Mock()
        SystemHelper mockSystemHelper = Mock()
        mockServiceLocator.getService(SystemHelper.class) >> mockSystemHelper
        mockSystemHelper.readConfigurationProperty(PersistenceConfigProperty.PERSISTENCE_TYPE) >> Optional.empty()
        PersistenceTypeService persistenceTypeService =
                new PersistenceTypeService(mockServiceLocator)
        String persistenceType
        when:
        persistenceType = persistenceTypeService.getPersistenceType()
        then:
        persistenceType == PersistenceTypeService.PERSISTENCE_TYPE_FILE
    }
}
