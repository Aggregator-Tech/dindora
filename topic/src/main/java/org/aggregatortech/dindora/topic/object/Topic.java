package org.aggregatortech.dindora.topic.object;

import org.aggregatortech.dindora.common.object.Entity;

import java.util.HashMap;
import java.util.Objects;

public class Topic extends Entity {

  public static enum AttributeNames {
    name, description;
  }

  public Topic() {
    attributes = new HashMap<String,String>();
  }

  public String getName() {
    return attributes.get(AttributeNames.name.toString());
  }

  public void setName(String name) {
    attributes.put(AttributeNames.name.toString(), name);
  }

  public String getDescription() {
    return attributes.get(AttributeNames.description.toString());
  }

  public void setDescription(String description) {
    attributes.put(AttributeNames.description.toString(), description);
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
    return Objects.equals(getId(), topic.getId())
        && Objects.equals(getName(), topic.getName())
        && Objects.equals(getDescription(), topic.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDescription());
  }
}
