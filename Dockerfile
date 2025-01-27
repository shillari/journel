# Use the official OpenJDK image as a base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your build output to the container
COPY target/journel-0.0.1-SNAPSHOT.jar journel-0.0.1-SNAPSHOT.jar

# Expose the port application runs on (e.g., 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "journel-0.0.1-SNAPSHOT.jar"]