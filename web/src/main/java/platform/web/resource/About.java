package platform.web.resource;

import platform.common.CommonConfigProperty;
import platform.common.io.system.SystemHelper;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("{serviceName}/about")
@Service
public class About {

  private SystemHelper systemHelper;

  @Inject
  public About(SystemHelper systemHelper) {
    this.systemHelper = systemHelper;
  }

  /**
   * Description of the service.
   * @param serviceName The service name.
   */
  @GET
  public String get(@PathParam("serviceName") String serviceName) {
    String about;
    about = systemHelper.readConfigurationProperty(CommonConfigProperty.SERVICE_DESCRIPTION)
            .orElse(serviceName);
    return about;
  }
}
