package platform.web.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import platform.common.CommonConfigProperty;
import platform.common.Constants;
import platform.common.ServiceLocatorHelper;
import platform.common.io.system.SystemHelper;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;


public class Server {
  // Base URI the Grizzly HTTP server will listen on
  public static final String BASE_URL = "http://" + NetworkListener.DEFAULT_NETWORK_HOST + ":%s/";

  private String getBaseUrl() {
    String port = System.getenv("PORT");
    if (port == null || port.isEmpty()) {
      port = ServiceLocatorHelper.getServiceLocator().getService(SystemHelper.class)
          .readMandatoryConfigurationProperty(CommonConfigProperty.SERVICE_PORT);
    }
    return String.format(BASE_URL, port);
  }

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
   *
   * @return Grizzly HTTP server.
   */
  public HttpServer startServer() {
    // create a resource config that scans for JAX-RS resources and providers
    final ResourceConfig rc = new ResourceConfig().packages(Constants.PLATFORM_PACKAGE);
    rc.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

    // create and start a new instance of grizzly http server
    // exposing the Jersey application at BASE_URL
    System.out.println("baseUrl: " + getBaseUrl());
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(getBaseUrl()), rc,
                                                        ServiceLocatorHelper.getServiceLocator());
  }




  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.startServer();
  }
}
