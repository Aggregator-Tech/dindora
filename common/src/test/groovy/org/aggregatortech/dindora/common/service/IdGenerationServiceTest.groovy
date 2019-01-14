package org.aggregatortech.dindora.common.service

import spock.lang.Specification

class IdGenerationServiceTest extends Specification {
    def "Test Generate"() {
        IdGenerationService idGenerationService = new IdGenerationService();
        String id;

        when:
        id = idGenerationService.generate()

        then:
        println "id = $id"
        id != null
    }
}
