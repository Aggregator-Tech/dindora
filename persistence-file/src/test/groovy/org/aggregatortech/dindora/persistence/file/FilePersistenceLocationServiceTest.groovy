package org.aggregatortech.dindora.persistence.file

import org.aggregatortech.dindora.common.io.system.SystemHelper
import spock.lang.Specification

class FilePersistenceLocationServiceTest extends Specification {
    def "Test GetLocation"() {
        FilePersistenceLocationService filePersistenceLocationService = new FilePersistenceLocationService()
        SystemHelper mockSystemHelper = Mock()
        filePersistenceLocationService.setSystemHelper(mockSystemHelper)
        String testLocation = "/test/blah"
        when:
        String location = filePersistenceLocationService.getLocation()

        then:
        1 * mockSystemHelper.readMandatoryConfigurationProperty(FilePersistenceConfigProperty.FILE_PERSISTENCE_LOCATION) >> testLocation
        location == testLocation

    }
}
