# api-gateway-service
The API Gateway Service is a component in the Instagram clone application that acts as a gateway or entry point for all incoming API requests. It provides a unified interface for clients to access various microservices within the system. The API Gateway handles request routing, load balancing, authentication, authorization, and other cross-cutting concerns.

## Features

- Request routing and load balancing
- Authentication and authorization

## Getting Started

These instructions will get you a copy of the Client Service up and running on your local machine for development and testing purposes.

## Technologies Used
- Java 17
- Spring Boot 3.1.0
- Maven 4.0.0

## Dependencies

- [Spring Boot] 
- [Spring Cloud Gateway]
- [Spring Cloud Eureka Client]


### Prerequisites

- [Java SE Development Kit 17.0.5] (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 4.0.0] (https://maven.apache.org/install.html)
- [Spring Boot CLI] (https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-installing-the-cli) (Optional)

### Installation
1. Clone the repository:
```bash
git clone <repository-url>
```
2. Navigate to the project directory::
```bash
cd api-gateway-service
```
4. Build the project using Maven:
```bash
cd api-gateway-service
```
5. Run the application:
```bash
mvn spring-boot:run
```
## Configuration
The API Gateway Service requires configuration to define routes and configure various filters and policies. Here are the common configurations you may need to modify
- **application.properties:** This file contains general application-level configurations, such as server port, service discovery configuration, and security settings.
- **application.yml**: This file defines the routes and their corresponding target services. You need to configure routes for each microservice you want to expose through the API Gateway.
