package org.aggregatortech.dindora.persistence.dynamodb;

import com.google.common.base.Strings;
import org.jvnet.hk2.annotations.Service;

@Service
public class DynamoDbHelper {
  public boolean checkConfiguration() {
    return true;
  }
}
