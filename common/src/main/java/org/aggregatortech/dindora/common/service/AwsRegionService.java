package org.aggregatortech.dindora.common.service;

import org.aggregatortech.dindora.common.ConfigProperty;
import org.aggregatortech.dindora.common.io.system.SystemHelper;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class AwsRegionService {

  enum AwsRegionConfigProperty implements ConfigProperty {
    AWS_REGION;
  }

  @Inject
  SystemHelper systemHelper;

  public String getRegion() {
    return systemHelper.readConfigurationProperty(AwsRegionConfigProperty.AWS_REGION).orElse(null);
  }
}
