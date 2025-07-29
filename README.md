# COIL Matching System

A Spring Boot application for matching university courses in COIL (Collaborative Online International Learning) partnerships.

## Overview

The COIL Matching System helps universities find compatible courses for international collaboration by matching courses based on language and thematic content. This system facilitates the creation of COIL partnerships between professors and universities worldwide.

## Features

- **Course Matching**: Automatically match courses based on language and thematic similarity
- **University Management**: Manage university information and course catalogs
- **Professor Profiles**: Track professor information and their associated courses
- **REST API**: RESTful web services for integration with other systems
- **Data Export**: Export matching results to Excel format

## Technology Stack

- **Java 17+** - Programming language
- **Spring Boot 3.x** - Application framework
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database for development
- **Jakarta Persistence API** - ORM specification
- **Maven/Gradle** - Build tool (to be configured)

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6+ or Gradle 7+
- IDE with Spring Boot support (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/GianTituana/coil-matching-system.git
cd coil-matching-system
```

2. Build the project:
```bash
# Using Maven
mvn clean install

# Using Gradle
./gradlew build
```

3. Run the application:
```bash
# Using Maven
mvn spring-boot:run

# Using Gradle
./gradlew bootRun

# Using JAR
java -jar target/coil-matching-system-*.jar
```

4. Access the application:
- API Base URL: `http://localhost:8080/api`
- H2 Console: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:coildb`
  - Username: `sa`
  - Password: (empty)

## API Documentation

### Course Matching Endpoint

**POST** `/api/cursos/matching`

Match courses based on language and thematic content.

**Request Body:**
```json
{
  "codigo": "CS101",
  "nombre": "Introduction to Computer Science",
  "idioma": "English",
  "tematica": "Computer Science"
}
```

**Response:**
```json
[
  {
    "codigo": "CS102",
    "nombre": "Programming Fundamentals",
    "idioma": "English",
    "tematica": "Computer Science",
    "universidad": {
      "nombre": "Partner University",
      "pais": "Germany"
    }
  }
]
```

## Database Schema

### Entities

#### Universidad (University)
- `nombre` (String, PK) - University name
- `pais` (String) - Country
- `programas` (List<Curso>) - Associated courses

#### Curso (Course)
- `codigo` (String, PK) - Course code
- `nombre` (String) - Course name
- `idioma` (String) - Language of instruction
- `tematica` (String) - Subject theme
- `universidad` (Universidad) - Associated university

#### Profesor (Professor)
- `email` (String, PK) - Professor email
- `nombre` (String) - Professor name
- `universidad` (Universidad) - Associated university
- `cursos` (List<Curso>) - Courses taught

## Project Structure

```
src/
├── controller/          # REST controllers
│   └── CursoController.java
├── model/              # JPA entities
│   ├── Curso.java
│   ├── Profesor.java
│   └── Universidad.java
├── repository/         # Data access layer
│   └── CursoRepository.java
├── service/           # Business logic
│   └── SistemaCOILService.java
└── export/           # Data export utilities
    └── ExcelExporter.java

resources/
└── application.properties  # Application configuration
```

## Configuration

### Database Configuration

The application uses H2 in-memory database by default. Configuration in `application.properties`:

```properties
# H2 Configuration
spring.datasource.url=jdbc:h2:mem:coildb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
```

### Production Database

For production environments, update the configuration to use a persistent database:

```properties
# PostgreSQL Example
spring.datasource.url=jdbc:postgresql://localhost:5432/coildb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
```

## Development

### Running Tests

```bash
# Maven
mvn test

# Gradle
./gradlew test
```

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Maintain consistent indentation (4 spaces)

### Adding New Features

1. Create feature branch: `git checkout -b feature/your-feature-name`
2. Implement changes following existing patterns
3. Add appropriate tests
4. Update documentation if needed
5. Submit pull request

## Deployment

### JAR Deployment

```bash
java -jar -Dspring.profiles.active=prod target/coil-matching-system-*.jar
```

### Docker Deployment

```dockerfile
FROM openjdk:17-jre-slim
COPY target/coil-matching-system-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For questions or support, please contact:
- Project Maintainer: [Gian Tituana](mailto:gian.tituana@example.com)
- Create an issue on GitHub for bug reports or feature requests

## Roadmap

- [ ] Add authentication and authorization
- [ ] Implement course recommendation algorithms
- [ ] Add support for multiple languages in UI
- [ ] Enhance matching criteria (duration, level, etc.)
- [ ] Add notification system for new matches
- [ ] Implement course rating and feedback system