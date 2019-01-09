package org.aggregatortech.dindora.persistence;

import org.aggregatortech.dindora.common.object.Entity;
import org.jvnet.hk2.annotations.Contract;

import java.util.List;

@Contract
public interface PersistenceService<T extends Entity> {

  T create(T entity);

  List<T> getAll();

  T get(String id);
}
