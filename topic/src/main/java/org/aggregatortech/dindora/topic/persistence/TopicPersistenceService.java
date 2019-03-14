package org.aggregatortech.dindora.topic.persistence;

import org.aggregatortech.dindora.persistence.PersistenceService;
import org.aggregatortech.dindora.topic.object.Topic;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface TopicPersistenceService extends PersistenceService<Topic> {

}
