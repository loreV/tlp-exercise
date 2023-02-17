# TLP Exercise

## Build
### Prerequisites
- JDK 17
- Maven 3

To run a full development build, run `mvn install -f root/pom.xml`

## Run

From the root `mvn --spring.config.location=/Users/lorenzoverri/IdeaProjects/Hikit-openapi-ert/backend/src/main/resources/application.yml`

### OpenAPI
By default, the openAPI v3 specs are exposed at `api/v1/api-docs`.
Alternatively, to interact directly with the REST API, the Swagger UI can be found at `api/v1/api-ui.html`.
