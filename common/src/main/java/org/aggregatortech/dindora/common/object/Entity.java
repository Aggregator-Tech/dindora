package org.aggregatortech.dindora.common.object;

import java.util.Map;

public abstract class Entity {

  public static enum AttributeNames {
    id;
  }
  protected Map<String,String> attributes;

  public String getId() {
    return attributes.get(AttributeNames.id.toString());
  }

  public void setId(String id) {
    attributes.put(AttributeNames.id.toString(), id);
  }

  public Map<String, String> allAttributes() {
    return attributes;
  }
}
