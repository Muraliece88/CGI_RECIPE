# CGI - Receipe CRUD REST API

## Frameworks Used

Java 17, Spring Boot 3, Maven 3.8.
For removing bolier plate code --- Lombok.
API Documentation - OpenApi 3.0.3.
Database-Embedded DB - H2.
Testing - Junit jupiter, Mockito, MockMvc Embedded DB - H2.
No checkstyles files used

## Code setup and run
1. Clone the project from gitHub
2. Navigate to project root folder and execute mvn spring-boot:run -Dspring-boot.run.profiles=prod (ensure maven is present on the machine)
3. Application is built as multi module maven project
4. Application runs on 9090 port

## Packaging structure

Considering microservice architecture functional packaging is considered and common components are treated to as modules
to avoid changes at multiple location

## Production ready considerations:

Spring boot application runs with active profiles hence properties per environment is placed and the prod profile is configured to point the H2 database. In the real time scenario, different databases can be used based on the environment.

Spring security is enabled with different credentials for prod with different roles for differnt endpoint. Roles can also be configured in tables and can be made run time rather than hardcoding

Actuator  endpoints are made available to capture health and metrics which is accessible for user with role SUPERUSER.

http://localhost:9090/actuator : With actuators operational informations like health, metrics, info, dump can be collected

Metrics sent by actuators can be intercepted by prometheus server for event monitoring and alerting

If application is hosted on cloud (Azure for instance), then ApplicationInsights can be condigured with Instrumentation key for monitoring and logAnalytics

Azure keyvault can be used to store and retrive secrets and certificates

Additional validation like TLS/MA and validating user token against AD can be done