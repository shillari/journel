# Use the official OpenJDK image as a base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy source code
COPY . .

# Build the jar 
RUN ./mvnw clean package -DskipTests

# Expose the port application runs on (e.g., 8080)
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "target/journel-0.0.1-SNAPSHOT.jar"]