# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/manager-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default for Spring Boot is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]