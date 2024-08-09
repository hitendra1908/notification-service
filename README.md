# NotificationService

NotificationService is an API that listens to a "freelancer_notification" Kafka topic, consumes messages and then stores it into Database.
Note: This service is using the same database and kafka from FreeLancerService(https://github.com/hitendra1908/freelancer-service.git) so we need to start freelancer-service first or at least the start the the docker.

## Technologies Used
This project utilizes the following technologies:
* Spring Boot 3.3.2
* Spring Data JPA
* PostgreSQL 14
* Maven 3.3.2
* Java 21
* JUnit 5
* Kafka
* [Testcontainers](https://testcontainers.com/) (for Spring integration tests using a container)


## How to Run the Project

1. Clone the repository:
   ```sh
   git clone https://github.com/hitendra1908/notification-service.git

2. Build the project: ( We need to run "Docker compose up" from FreelancerService first)
   ```sh
   mvn clean install

3. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run

## To View Kafka Messages
Kafka messages can be seen in the kafka-ui at http://localhost:8090/

## Endpoint
Endpoint "/notifications/{userName}" is provided to fetch notifications stored in database for a user(freelancer).
