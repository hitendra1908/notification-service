# NotificationService

NotificationService is an API that listens to a "freelancer_notification" Kafka topic, consumes messages and then store into Database.

## Technologies Used
This project utilizes the following technologies:
* Spring Boot 3.3.2
* Spring Data JPA
* PostgreSQL 14
* Maven 3.3.2
* Java 21
* JUnit 5
* Kafka

## How to Run the Project

1. Clone the repository:
   ```sh
   git clone https://github.com/hitendra1908/notification-service.git

2. Build the project:
   ```sh
   mvn clean install

3Run the Spring Boot application:
   ```sh
   mvn spring-boot:run

## To View Kafka Messages
Kafka messages can be seen in the kafka-ui at http://localhost:8090/

## Endpoint
Endpoint "/notifications/{userName}" is provided to fetch notifications stored in database for a user(freelancer).
