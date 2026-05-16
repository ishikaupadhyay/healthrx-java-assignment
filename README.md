# HealthRx Java Assignment

This is a Spring Boot 3 application designed to automate a multi-step API integration flow upon startup.

## Features
- Spring Boot 3.2.5
- Java 17
- WebClient for asynchronous API calls
- Lombok for boilerplate reduction
- CommandLineRunner for automated execution on startup

## Flow
1. **Webhook Generation**: Calls `POST /generateWebhook/JAVA` with user details.
2. **Response Parsing**: Extracts `webhook` and `accessToken`.
3. **Query Logic**: Based on the registration number ending in `39` (odd), Question 1 logic is applied (`SELECT 1`).
4. **Test Webhook**: Submits the final query to `POST /testWebhook/JAVA` with the acquired access token.

## Prerequisites
- Java 17
- Maven 3.x

## How to Run

### Run Locally
```bash
mvn spring-boot:run
```

### Build Executable JAR
```bash
mvn clean package
```
The JAR will be located in the `target/` directory.

### Run JAR
```bash
java -jar target/healthrx-java-assignment-0.0.1-SNAPSHOT.jar
```

## Git Commands
```bash
git init
git add .
git commit -m "Initial commit: Spring Boot automation flow"
```

## Author
- Ishika Upadhyay
- Reg No: 0827CD231039
- Email: ishikaupadhyay230142@acropolis.in
