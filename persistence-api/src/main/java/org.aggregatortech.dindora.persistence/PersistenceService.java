package org.aggregatortech.dindora.persistence;

import org.aggregatortech.dindora.common.object.Entity;
import org.jvnet.hk2.annotations.Contract;

import java.util.List;

@Contract
public interface PersistenceService<T extends Entity> {
  public List<T> search();

  public T create(T t);
}
