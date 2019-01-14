package org.aggregatortech.dindora.topic.object;

import org.aggregatortech.dindora.common.object.Entity;

import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Topic topic = (Topic) obj;
    return Objects.equals(id, topic.id)
        && Objects.equals(name, topic.name)
        && Objects.equals(description, topic.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }
}
