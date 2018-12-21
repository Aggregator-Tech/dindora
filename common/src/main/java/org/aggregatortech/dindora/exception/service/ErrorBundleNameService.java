package org.aggregatortech.dindora.exception.service;

import org.jvnet.hk2.annotations.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ErrorBundleNameService {

  public String getErrorBundleName(String errorCode) {
    String errorBundleName = null;
    Pattern pattern = Pattern.compile("([A-Z]+_[A-Z]+)(_\\w+)");
    Matcher matcher = pattern.matcher(errorCode);
    if (matcher.matches()) {
      errorBundleName = matcher.group(1);
    }
    return errorBundleName;
  }
}
