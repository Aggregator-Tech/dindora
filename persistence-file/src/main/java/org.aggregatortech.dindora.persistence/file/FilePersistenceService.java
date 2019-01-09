package org.aggregatortech.dindora.persistence.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aggregatortech.dindora.common.object.Entity;
import org.aggregatortech.dindora.common.service.BaseService;
import org.aggregatortech.dindora.common.service.IdGenerationService;
import org.aggregatortech.dindora.persistence.PersistenceService;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class FilePersistenceService<T extends Entity>
    extends BaseService
    implements PersistenceService<T> {

  @Inject
  private FilePersistenceLocationService filePersistenceLocationService;
  @Inject
  private IdGenerationService idGenerationService;

  protected ObjectMapper mapper;
  protected File persistenceFile;
  protected List<T> entities;

  public FilePersistenceService() {
    this.mapper = new ObjectMapper();
    entities = new ArrayList<T>();
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  public File getPersistenceFile() {
    if (persistenceFile == null) {
      String filePersistenceLocation = filePersistenceLocationService.getLocation();
      persistenceFile = new File(filePersistenceLocation);
    }
    return persistenceFile;
  }

  public List<T> getEntities() {
    return entities;
  }

  public List<T> getAll() {
    entities = readData();
    return entities;
  }

  protected abstract List<T> readData();

  public T create(T entity) {
    entity.setId(idGenerationService.generate());
    entities.add(entity);
    writeData();
    return entity;
  }

  protected abstract void writeData();

  public void setFilePersistenceLocationService(FilePersistenceLocationService filePersistenceLocationService) {
    this.filePersistenceLocationService = filePersistenceLocationService;
  }

  @Override
  public T get(String id) {
    Optional<T> topic = getEntities().stream().filter(entity -> entity.getId().equals(id)).findFirst();
    return topic.get();
  }
}
