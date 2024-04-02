# Random Jokes Application

A Java SpringBoot application to extract a random joke from an external API.

View [`Architectural Choice Document`](./documents/architectural.md) and [`HELP resource Document`](./HELP.md)

# README

This README would normally document whatever steps are necessary to get the application up and running.

## What is this repository for?

- REST APIs for retrieving random jokes.

## Dependencies

- Java 17
- Maven

### Prerequisites

Ensure you have the following tools installed:

| Name                        | Download                                              |                                             |
|-----------------------------|-------------------------------------------------------|---------------------------------------------|
| Java                        | https://adoptium.net/temurin/releases/                | JDK 17 or newer                             |
| Maven                       | https://maven.apache.org/download.cgi                 | Download the Binary and extract as needed   |
| Docker                      | https://www.docker.com/                               |                                             |
| IntelliJ IDEA OR Other IDEA | https://www.jetbrains.com/idea/download/other.html    | IntelliJ The community edition is also fine |
| WSL                         | https://learn.microsoft.com/en-us/windows/wsl/install | If using Windows and Docker+WSL             |
| Git                         | https://git-scm.com/                                  | Needed to clone repository                  |

### Environment Variables

Ensure that you have added your Java and Maven installations to your `PATH` and that the `JAVA_HOME` and `MAVEN_HOME`
environment variables are set appropriately

## Getting Started

To clone repository, run the following commands on a terminal:

`git clone https://github.com/msundlana/random-joke-api.git` this will clone the repository onto your local machine.

### Running the API

To run the api you will need to do the following

1. Setting up the application
    1. Install dependencies using `mvn clean install` or `mvn clean install -P ${PROFILE_NAME}`
       `${PROFILE_NAME} = dev|prod|test`
    2. If running through the terminal
        1. Build the application using `mvn package`
        2. Start the app
           using `mvn spring-boot:run` ([Other Spring Boot Scripts here](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#goals))
    3. If running with your IDE just hit the play button on `RecipeManagementServiceApplication.java`
    4. If running using docker 
       - Update [`.env`](.env) file with environmental variables used to configure the database 
        connection settings in the [`application-prod.properties`](./src/main/resources/application-prod.properties) directory:
        `USERNAME=your_username
         PASSWORD=your_password
       `
       - Replace `your_username` and `your_password`with your actual user credentials (Spring boot security currently disabled) and
       - To build the API docker image, run  `mvn spring-boot:build-image -P ${PROFILE_NAME}`
         `${PROFILE_NAME} = dev|prod|test`
       - To run the docker image, run `docker-compose up -d`
   5. The application will start
   6. Once the App is running, you can access it by navigating to `http://localhost:8080` in your web browser. 
   7. Once the App is running you can view the OpenAPI Doc at `http://localhost:8080/swagger-ui/index.html`
   8. Once the App is running you can also view `http://localhost:8080/actuator`
       and the health of the application on `http://localhost:8080/actuator/health`. Monitoring our app, gathering
       metrics, and understanding traffic or the state of our database is trivial.
       The actuator mainly exposes operational information about the running application — health, metrics, info, dump,
       env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.
   9. For Distributed Tracing http://localhost:9411/zipkin, only configured under the `prod` profile allowing.


### Running the tests

This project uses the [spring-boot-starter-test](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/boot-features-testing.html) framework with the
[Maven Artifact](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) plugin to run the full suite of tests.
Tests are annotation with @Test .

To run all tests simply execute `mvn test` from the project root. Alternatively if you are using Intellij right-click on the respective xml file and select run.

## Contribution guidelines

- Writing tests
- Code review
- Other guidelines





