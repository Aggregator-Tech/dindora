package org.aggregatortech.dindora.topic.object;

import org.aggregatortech.dindora.common.object.Entity;

public class Topic extends Entity {
  private String name;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
