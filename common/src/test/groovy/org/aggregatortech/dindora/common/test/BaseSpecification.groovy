package org.aggregatortech.dindora.common.test

import org.glassfish.hk2.api.ServiceLocator
import org.aggregatortech.dindora.common.ServiceLocatorHelper
import spock.lang.Shared
import spock.lang.Specification

class BaseSpecification extends Specification{
    @Shared static ServiceLocator serviceLocator;

    static {
        serviceLocator = ServiceLocatorHelper.serviceLocator;
    }

    def setupSpec() {
        println "setup BaseSpecification";
    }
}
