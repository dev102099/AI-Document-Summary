# Use Amazon Corretto 21 base image (Java 21)
FROM amazoncorretto:21-alpine

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first
COPY pom.xml ./
RUN apk add --no-cache maven && mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# Package the app
RUN mvn clean package -DskipTests

# Run the Spring Boot app
CMD ["java", "-jar", "target/*.jar"]
