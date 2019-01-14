package org.aggregatortech.dindora.persistence

import org.aggregatortech.dindora.common.io.system.SystemHelper
import spock.lang.Specification

class PersistenceTypeServiceTest extends Specification {
    def "Get PersistenceType"() {
        SystemHelper mockSystemHelper = Mock()
        mockSystemHelper.readConfigurationProperty(PersistenceConfigProperty.PERSISTENCE_TYPE) >> Optional.of(PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB)
        PersistenceTypeService persistenceTypeService = new PersistenceTypeService()
        persistenceTypeService.setSystemHelper(mockSystemHelper)
        String persistenceType
        when:
        persistenceType = persistenceTypeService.getPersistenceType()
        then:
        persistenceType == PersistenceTypeService.PERSISTENCE_TYPE_DYNAMO_DB
    }

    def "Get default PersistenceType"() {
        SystemHelper mockSystemHelper = Mock()
        mockSystemHelper.readConfigurationProperty(PersistenceConfigProperty.PERSISTENCE_TYPE) >> Optional.empty()
        PersistenceTypeService persistenceTypeService =
                new PersistenceTypeService()
        persistenceTypeService.setSystemHelper(mockSystemHelper)
        String persistenceType
        when:
        persistenceType = persistenceTypeService.getPersistenceType()
        then:
        persistenceType == PersistenceTypeService.PERSISTENCE_TYPE_FILE
    }
}
