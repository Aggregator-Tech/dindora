package org.aggregatortech.dindora.common.service

import org.aggregatortech.dindora.common.test.BaseSpecification

class AwsRegionServiceTest extends BaseSpecification {
    def "Test GetRegion"() {
        expect:
        getServiceLocator().getService(AwsRegionService.class).getRegion() == "us-west-2"
    }
}
