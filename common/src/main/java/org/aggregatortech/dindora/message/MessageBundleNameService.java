package org.aggregatortech.dindora.message;

import org.jvnet.hk2.annotations.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageBundleNameService {

  public String getMessageBundleName(String messageCode) {
    String messageBundleName = null;
    Pattern pattern = Pattern.compile("([A-Z]+_[A-Z]+)(_\\w+)");
    Matcher matcher = pattern.matcher(messageCode);
    if (matcher.matches()) {
      messageBundleName = matcher.group(1);
    }
    return messageBundleName;
  }
}
