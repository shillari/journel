# Use the official OpenJDK image as a base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy source code
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the jar 
RUN ./mvnw clean package -DskipTests

# Command to run the application
CMD ["java", "-jar", "target/journel-0.0.1-SNAPSHOT.jar"]