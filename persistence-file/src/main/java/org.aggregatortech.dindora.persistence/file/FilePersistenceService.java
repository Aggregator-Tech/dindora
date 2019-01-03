package org.aggregatortech.dindora.persistence.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aggregatortech.dindora.common.object.Entity;
import org.aggregatortech.dindora.common.service.IdGenerationService;
import org.aggregatortech.dindora.persistence.PersistenceService;
import org.glassfish.hk2.api.ServiceLocator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class FilePersistenceService<T extends Entity> implements PersistenceService<T> {

  protected ObjectMapper mapper;
  protected File persistenceFile;
  protected List<T> entities;
  ServiceLocator serviceLocator;
  public FilePersistenceService(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
    this.mapper = new ObjectMapper();
    entities = new ArrayList<T>();
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  public File getPersistenceFile() {
    if(persistenceFile == null) {
      String filePersistenceLocation = serviceLocator.getService(FilePersistenceLocationService.class)
          .getLocation();
      persistenceFile = new File(filePersistenceLocation);
    }
    return persistenceFile;
  }

  public List<T> getEntities() {
    return entities;
  }

  public List<T> search() {
    entities = readData();
    return entities;
  }

  protected abstract List<T> readData();

  public T create(T t) {
    t.setId(serviceLocator.getService(IdGenerationService.class).generate());
    entities.add(t);
    writeData();
    return t;
  }

  protected abstract void writeData();
}
