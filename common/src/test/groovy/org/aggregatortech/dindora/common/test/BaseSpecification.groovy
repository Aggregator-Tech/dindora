package org.aggregatortech.dindora.common.test

import org.aggregatortech.dindora.common.ServiceLocatorHelper
import org.glassfish.hk2.api.ServiceLocator


import spock.lang.Shared
import spock.lang.Specification

class BaseSpecification extends Specification{
    @Shared ServiceLocator serviceLocator;

    def setupSpec() {
        println "setup BaseSpecification";
        serviceLocator = ServiceLocatorHelper.serviceLocator;
    }
}
