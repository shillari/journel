# 📖 Journel Backend

Journel Backend is a Spring Boot application that provides a RESTful API for managing users, journal entries, categories, and tags. It integrates with PostgreSQL for persistent storage and Redis for caching frequently accessed data. The application supports JWT authentication and is containerized using Docker.

## ✨ Features
- User authentication with JWT
- CRUD operations for journal entries, categories, and tags
- PostgreSQL database for persistent storage
- Redis caching for improved performance
- Docker support for containerized deployment
- RESTful API design

## 🛠️ Technologies Used
- **Spring Boot** (Spring Data JPA, Spring Security)
- **PostgreSQL** (via Supabase)
- **Redis** (for caching tags and filtering entries)
- **Docker** (for containerized deployment)
- **Flyway** (for database migrations)
- **Hibernate** (ORM for PostgreSQL)
- **Maven** (for dependency management)

## 🔧 Installation (Development Environment)

### Prerequisites
Make sure you have installed:
- Java 17+
- Maven
- Docker & Docker Compose
- PostgreSQL (or use Supabase as an alternative)
- Redis

### Setup Instructions
1. **Clone the Repository**
   ```sh
   git clone https://github.com/your-repo/journel-backend.git
   cd journel-backend
   ```

2. **Set Up Environment Variables**
   - Create an `.env` file in the project root and configure your database credentials:
     ```env
     SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-url:5432/journel
     SPRING_DATASOURCE_USERNAME=your-db-username
     SPRING_DATASOURCE_PASSWORD=your-db-password
     SPRING_REDIS_HOST=localhost
     SPRING_REDIS_PORT=6379
     SECRET_ENC_KEY=your-secret-key
     ```

3. **Run the Application Locally**
   ```sh
   mvn spring-boot:run
   ```
   The backend should be available at `http://localhost:8081`.

## 🐳 Running with Docker

### 1. Build and Run the Docker Containers
```sh
docker-compose up --build
```
This will:
- Start the Spring Boot backend
- Spin up a Redis container
- Connect to your PostgreSQL database (Supabase or local)

### 2. Stop Containers
```sh
docker-compose down
```

## 🔗 Useful Commands
### Run Migrations with Flyway
```sh
mvn flyway:migrate
```
### Access Redis CLI in the Container
```sh
docker exec -it journel-redis redis-cli
```
