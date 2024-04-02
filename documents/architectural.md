# Architectural Choices Documentation

## Framework:

##### **Choice:** Spring Boot

###### Reasoning:

- `Spring Boot` provides a robust and comprehensive framework for building RESTful APIs in Java.
- Its convention-over-configuration approach reduces boilerplate code and simplifies setup.
- Additionally, Spring Boot offers extensive support for dependency injection, data access, testing, and documentation, making it an ideal choice for this project.

## Testing Strategy:

##### Unit Tests: 

- Unit tests are written for individual components such as service classes and repository classes. 
  Mocking frameworks like Mockito is used to isolate dependencies and ensure that each component behaves as expected in isolation.

##### Integration Tests: 

- Integration tests are written to verify the interaction between different layers of the application, including the 
  REST endpoints, service layer, and database. Spring Boot Test and MockMvc are used to simulate HTTP requests and test
    the application's behavior in a real-world scenario.

###### Reasoning:

- A combination of unit tests and integration tests ensures comprehensive test coverage, helping to identify
 and prevent bugs throughout the development process. By testing both individual components and the integration between
 them, we can verify the correctness and reliability of the entire system.

## Design Patterns:

##### Repository Pattern:

- The repository pattern is used to abstract data access and provide a clean separation between the business logic and data access layers.

- Spring RestTemplate is leveraged to make request calls to the External API.

##### Service Layer:

- The service layer is used to encapsulate business logic and orchestrate interactions between different components of the application.

- This helps to keep the controllers thin and focused on handling HTTP requests, while business logic resides in service classes.

###### Reasoning: 

- By following established design patterns like the repository pattern and service layer pattern, we promote code
 maintainability, scalability, and testability. These patterns encourage modularization and separation of concerns,
 making the codebase easier to understand and maintain over time.

##### Exception Handling:

- Added exception handling because it's essential to handle potential errors gracefully.
- Application is built to be resilient using fault tolerance mechanism by leveraging `Resilence4J` 

##### Logging and Tracing:

- Application uses `Log4j` which comes by default with Spring boot and `Zipkin Tracing Server` for prod environments.
- Logging in Spring Boot plays a vital role in Spring Boot applications for recording information, actions, 
  and events within the app. It is also used for monitoring the performance of an application, understanding the behavior of the application, and recognizing the issues within the application.

#### Monitoring application Health:

- `Spring boot Actuator` is used for Monitoring the app as gathering metrics, and understanding traffic or the state of our
 database is trivial. Actuator provides all of Spring Boot's production-ready features.

#### Code Documentation: 

- `SpringDoc OpenAPI` is used together with `Swagger_UI API` to generate document and UI clients can connect
  with it to auto generate services to reduce API contract violation

#### Live reloads

- `Spring Boot Dev Tools` for to provide fast application restarts, LiveReload, and configurations
  for enhanced development experience.

#### Reduce Code Boiler plates

- `Lombok` used to reduce boilerplate code.

> Overall, these architectural choices aim to provide a solid foundation for building a production-ready 
> Java SpringBoot application to extract a random joke from an external API. The combination of Spring Boot, thorough testing, 
> and design patterns which helps to ensure that the application is robust, scalable, and maintainable.