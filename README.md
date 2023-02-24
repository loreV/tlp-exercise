# TLP Exercise
An exercise to get hired!

## Build
### Prerequisites
- JDK 17
- Maven 3

To run a full development build, run `mvn install -f root/pom.xml`

## Run
First, package the project by running: `mvn package -f ./root/pom.xml -P package`

Then, execute the application by `java -jar backend/target/TLPCustomerService-spring-boot.jar --spring.config.location=backend/src/main/resources/application.yml`

The service is up and running at `http://localhost:8991/api/v1/swagger-ui/index.html`.

### OpenAPI
By default, the openAPI v3 specs are exposed at `api/v1/api-docs`.
Alternatively, to interact directly with the REST API, the Swagger UI can be found at `api/v1/api-ui.html`.

## Testing
Two types of tests are included in this project repo: unit and integration tests (the latter with naming format `.*IntegrationTest.java`).

## Persistence
Data is temporarily persisted by in-mem H2 DB, for dev comfort.
