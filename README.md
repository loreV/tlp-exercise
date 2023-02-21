# TLP Exercise

## Build
### Prerequisites
- JDK 17
- Maven 3

To run a full development build, run `mvn install -f root/pom.xml`

## Run
First, package the project by running: `mvn package -f ./root/pom.xml -P package`

Then, execute the application by `java -jar backend/target/TLPCustomerService-spring-boot.jar --spring.config.location=backend/src/main/resources/application.yml`

The service is up and running at `http://localhost:8991/api/v1/swagger-ui/index.html`.

## Testing
As the project serves only as an exercise, the code coverage is purposely low: only some tests were written in order to provide room for discussion.

### OpenAPI
By default, the openAPI v3 specs are exposed at `api/v1/api-docs`.
Alternatively, to interact directly with the REST API, the Swagger UI can be found at `api/v1/api-ui.html`.
