package org.aggregatortech.dindora.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aggregatortech.dindora.common.object.Entity;
import org.aggregatortech.dindora.common.service.IdGenerationService;
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
    String filePersistenceLocation = serviceLocator.getService(FilePersistenceLocationService.class)
        .getLocation();
    persistenceFile = new File(filePersistenceLocation);
    entities = new ArrayList<T>();
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  public void setMapper(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public File getPersistenceFile() {
    return persistenceFile;
  }

  public void setPersistenceFile(File persistenceFile) {
    this.persistenceFile = persistenceFile;
  }

  public List<T> getEntities() {
    return entities;
  }

  public void setEntities(List<T> entities) {
    this.entities = entities;
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
