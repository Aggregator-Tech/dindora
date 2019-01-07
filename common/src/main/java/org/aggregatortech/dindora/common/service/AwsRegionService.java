package org.aggregatortech.dindora.common.service;

import org.jvnet.hk2.annotations.Service;

@Service
public class AwsRegionService {
  enum AwsRegions {
    US_WEST_2("us-west-2");

    private String id;
    AwsRegions(String id) {
      this.id = id;
    }

    public String getId() {
      return id;
    }
  }

  public String getRegion() {
    return AwsRegions.US_WEST_2.getId();
  }
}
