# Build instructions

## Environment Settings
* set JAVA_HOME environment variable to point to latest JDK 8 installation.
* If you are running behind proxy, then update the proxy settings in gradle.properties.

##  Compile and run unit tests
$gradlew build

## Assembly
Note: Assembly is applicable for only the deployable modules like webTemplate.
### As standalone  application
$ gradlew installDist
* For webTemplate project,  webTemplate/build/distributions  directory will contain the binaries for the application  packages as zip and tar files. 
* The  executable application will also be installed under webTemplate/build/install/webTemplate directory. 
* The service port can be configured by setting the servicePort property in the webTemplate/build.gradle.
* The service port can also be specified while running the executable by setting the JAVA_OPTS environment varible to "-DservicePort=<port>"
  
## Integration Testing
* Integration test pertaining to sub project reside under <subProjectRoot>/src/integrationTest directory

To run integration tests for the all projects under platform ( Note: this will not work till we have LBR enabled, till then we will have to run project level integ Tests with respective urls)
$gradlew integrationTest -DbaseUrl=<baseUrl of the microservice>

For example ./gradlew integrationTest -DbaseUrl=<LBR Url>

To run integration tests for specific sub project under platform, say webTemplate
$gradlew :webTemplate:integrationTest -DbaseUrl=http://localhost:9499

