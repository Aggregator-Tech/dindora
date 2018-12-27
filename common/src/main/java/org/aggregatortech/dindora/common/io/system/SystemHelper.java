package org.aggregatortech.dindora.common.io.system;

import com.google.common.base.CaseFormat;
import org.jvnet.hk2.annotations.Service;
import org.aggregatortech.dindora.common.ConfigProperty;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@Service
public class SystemHelper {

  /**
   * Read the value of configuration property with the following precedence:
   * 1. Environment variable.
   * 2. Java system property.
   * If the configuration property is not specified, a RuntimeException will be thrown.
   * @param configProperty The configuration property name.
   */
  public String readMandatoryConfigurationProperty(ConfigProperty configProperty) {
    Optional<String> configPropertyValue = readConfigurationProperty(configProperty, null);
    if (configPropertyValue.isPresent()) {
      return configPropertyValue.get();
    } else {
      throw new RuntimeException("Failed to read configuration property: "
          + getConfigPropertyAsString(configProperty));
    }
  }

  /**
   * Read the value of configuration property with the following precedence:
   * 1. Environment variable.
   * 2. Java system property.
   *
 * @param configProperty The configuration property name.
   */
  public Optional<String> readConfigurationProperty(ConfigProperty configProperty) {
    return readConfigurationProperty(configProperty, null);
  }

  /**
   * Read the value of configuration property with the following precedence:
   * 1. Environment variable.
   * 2. Java system property.
   *
   * @param configProperty The configuration property name.
   * @param defaultValue The default value if configuration property not specified.
   */
  public Optional<String> readConfigurationProperty(ConfigProperty configProperty,
                                                    String defaultValue) {
    String propertyName = getConfigPropertyAsString(configProperty);
    return readConfigurationProperty(propertyName,defaultValue);
  }
  /**
   * Read the value of configuration property with the following precedence:
   * 1. Environment variable.
   * 2. Java system property.
   *
   * @param propertyName The configuration property name as String
   * @param defaultValue The default value if configuration property not specified.
   */
  public Optional<String> readConfigurationProperty(String propertyName, String defaultValue) {
    String propertyValue;
    propertyValue = System.getenv(propertyName);
    if (propertyValue == null) {
      propertyValue = System.getProperty(propertyName);
    }
    if (propertyValue == null) {
      propertyValue = defaultValue;
    }
    return Optional.ofNullable(propertyValue);
  }

  /**
   * Write the value of a configuration property.
   * @param configProperty The configuration property.
   * @param value The value.
   */
  public void writeConfigurationProperty(ConfigProperty configProperty,
                                    String value) {

   writeProperty(getConfigPropertyAsString(configProperty), value);
  }

  /**
   * Write the value of a configuration property.
   * @param propName The configuration property.
   * @param value The value.
   */
  public void writeProperty( String propName, String value) {

    System.setProperty(propName, value);
  }

  public String getConfigPropertyAsString(ConfigProperty configProperty) {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, configProperty.toString());
  }



}
