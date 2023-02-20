# TLP Exercise

## Build
### Prerequisites
- JDK 17
- Maven 3

To run a full development build, run `mvn install -f root/pom.xml`

## Run
Package and run: `mvn package -f ./root/pom.xml -P package`
Execute the application `java -jar backend/target/TLPCustomerService-spring-boot.jar --spring.config.location=backend/src/main/resources/application.yml`
Default service port is 8991.

### OpenAPI
By default, the openAPI v3 specs are exposed at `api/v1/api-docs`.
Alternatively, to interact directly with the REST API, the Swagger UI can be found at `api/v1/api-ui.html`.
