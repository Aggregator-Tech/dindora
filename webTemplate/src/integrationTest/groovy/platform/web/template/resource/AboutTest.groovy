package platform.web.template.resource

import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget

class AboutTest extends Specification{
    @Shared
    String baseUrl;

    def setup() {
        baseUrl = System.getProperty("baseUrl");
    }
    def testGetAbout() {
        setup:
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        WebTarget target = clientBuilder.build().target(baseUrl).path("/webTemplate/about")

        when:
        String response = target.request().get(String.class)
        println "response = $response"

        then:
        assert response == "webTemplate"
    }
}
