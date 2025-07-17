# Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-21 as build

WORKDIR /app

# Copy pom.xml and download dependencies first
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Use a smaller image to run the app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]

