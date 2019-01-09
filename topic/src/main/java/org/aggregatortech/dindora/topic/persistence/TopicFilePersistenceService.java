package org.aggregatortech.dindora.topic.persistence;

import com.fasterxml.jackson.databind.type.CollectionType;
import org.aggregatortech.dindora.exception.ProcessingException;
import org.aggregatortech.dindora.persistence.PersistenceTypeService;
import org.aggregatortech.dindora.persistence.file.FilePersistenceService;
import org.aggregatortech.dindora.topic.message.bundle.TopicMessages;
import org.aggregatortech.dindora.topic.object.Topic;
import org.jvnet.hk2.annotations.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service(name = PersistenceTypeService.PERSISTENCE_TYPE_FILE)
public class TopicFilePersistenceService extends FilePersistenceService<Topic>
    implements TopicPersistenceService {

  @Override
  protected List<Topic> readData() {
    List<Topic> topics = null;
    try {
      CollectionType javaType = getMapper().getTypeFactory()
          .constructCollectionType(List.class, Topic.class);
      topics = getMapper().readValue(getPersistenceFile(), javaType);
    } catch (IOException e) {
      throw buildException();
    }
    return topics;
  }

  @Override
  protected void writeData() {
    try {
      getMapper().writeValue(getPersistenceFile(), getEntities());
    } catch (IOException e) {
      throw buildException();
    }
  }

  private ProcessingException buildException() {
    return getExceptionService().buildException(new ProcessingException(TopicMessages.DINDORA_TOPIC_PERSISTENCE_FAILED.toString()));
  }

}
