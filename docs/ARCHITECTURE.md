# Architecture Documentation

## System Overview

The COIL Matching System follows a layered architecture pattern based on Spring Boot framework, designed to facilitate course matching for international university collaborations.

## High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Client Apps   │    │   Web Browser   │    │  API Clients    │
│                 │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                ┌─────────────────▼─────────────────┐
                │          REST API Layer          │
                │        (Spring Web MVC)          │
                └─────────────────┬─────────────────┘
                                 │
                ┌─────────────────▼─────────────────┐
                │        Service Layer             │
                │      (Business Logic)            │
                └─────────────────┬─────────────────┘
                                 │
                ┌─────────────────▼─────────────────┐
                │     Repository Layer             │
                │    (Data Access - JPA)           │
                └─────────────────┬─────────────────┘
                                 │
                ┌─────────────────▼─────────────────┐
                │         H2 Database              │
                │       (In-Memory)                │
                └─────────────────────────────────────┘
```

## Layer Breakdown

### 1. Presentation Layer (Controller)

**Location:** `src/controller/`

**Responsibilities:**
- Handle HTTP requests and responses
- Input validation and transformation
- API endpoint definitions
- Exception handling

**Components:**
- `CursoController.java` - REST endpoints for course operations

**Key Patterns:**
- RESTful API design
- JSON serialization/deserialization
- HTTP status code management

### 2. Service Layer

**Location:** `src/service/`

**Responsibilities:**
- Business logic implementation
- Transaction management
- Data validation
- Orchestration of multiple repository calls

**Components:**
- `SistemaCOILService.java` - Core matching logic

**Key Patterns:**
- Service facade pattern
- Dependency injection
- Transaction boundaries

### 3. Repository Layer

**Location:** `src/repository/`

**Responsibilities:**
- Data access abstraction
- Query implementation
- Database operations

**Components:**
- `CursoRepository.java` - Course data access

**Key Patterns:**
- Repository pattern
- Spring Data JPA
- Query by example

### 4. Model Layer

**Location:** `src/model/`

**Responsibilities:**
- Entity definitions
- Data relationships
- Persistence mapping

**Components:**
- `Curso.java` - Course entity
- `Profesor.java` - Professor entity
- `Universidad.java` - University entity

**Key Patterns:**
- JPA entity mapping
- Domain-driven design
- Object-relational mapping

## Data Flow

### Course Matching Request Flow

```
1. Client Request
   │
   ▼
2. CursoController.matchCursos()
   │ - Receives HTTP POST request
   │ - Validates request body
   │
   ▼
3. SistemaCOILService.realizarMatching()
   │ - Implements business logic
   │ - Calls repository methods
   │
   ▼
4. CursoRepository.findByIdiomaAndTematica()
   │ - Executes database query
   │ - Returns matching courses
   │
   ▼
5. Response Processing
   │ - Convert entities to DTOs
   │ - Serialize to JSON
   │
   ▼
6. HTTP Response to Client
```

## Database Design

### Entity Relationship Diagram

```
┌─────────────────┐       ┌─────────────────┐       ┌─────────────────┐
│   Universidad   │       │      Curso      │       │    Profesor     │
│─────────────────│       │─────────────────│       │─────────────────│
│ nombre (PK)     │◄──────│ codigo (PK)     │       │ email (PK)      │
│ pais            │   1:N │ nombre          │       │ nombre          │
│                 │       │ idioma          │       │                 │
└─────────────────┘       │ tematica        │       └─────────────────┘
                          │ universidad_id  │                 │
                          └─────────────────┘                 │
                                    ▲                         │
                                    │                         │
                                    │ M:N                     │
                                    │                         │
                          ┌─────────────────┐                 │
                          │ profesor_curso  │◄────────────────┘
                          │─────────────────│
                          │ profesor_email  │
                          │ curso_codigo    │
                          └─────────────────┘
```

### Relationships

1. **Universidad → Curso**: One-to-Many
   - One university can offer multiple courses
   - Each course belongs to one university

2. **Profesor → Universidad**: Many-to-One
   - Multiple professors can work at one university
   - Each professor is associated with one university

3. **Profesor ↔ Curso**: Many-to-Many
   - Professors can teach multiple courses
   - Courses can be taught by multiple professors

## Technology Stack Details

### Core Framework
- **Spring Boot 3.x**: Main application framework
- **Spring Web MVC**: REST API implementation
- **Spring Data JPA**: Data persistence layer
- **Spring IoC Container**: Dependency injection

### Data Layer
- **Hibernate**: JPA implementation
- **H2 Database**: In-memory database for development
- **Jakarta Persistence API**: Java persistence standard

### Build and Deployment
- **Maven/Gradle**: Build tool (to be configured)
- **Java 17+**: Programming language
- **Spring Boot Actuator**: Production monitoring (planned)

## Design Patterns

### 1. Repository Pattern
```java
public interface CursoRepository extends JpaRepository<Curso, String> {
    List<Curso> findByIdiomaAndTematica(String idioma, String tematica);
}
```

### 2. Service Layer Pattern
```java
@Service
public class SistemaCOILService {
    @Autowired
    private CursoRepository cursoRepository;
    
    public List<Curso> realizarMatching(Curso cursoReferencia) {
        // Business logic implementation
    }
}
```

### 3. Controller Pattern
```java
@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private SistemaCOILService sistemaCOILService;
}
```

### 4. Dependency Injection
- Constructor injection (recommended)
- Field injection (currently used)
- Setter injection

## Cross-Cutting Concerns

### Error Handling
- Spring Boot's default error handling
- Global exception handling (to be implemented)
- Validation error responses

### Logging
- Spring Boot's default logging (Logback)
- Console output configuration
- Log levels configuration

### Configuration
- `application.properties` for environment-specific settings
- Profile-based configuration support
- External configuration support

## Security Considerations

### Current State
- No authentication/authorization implemented
- All endpoints are publicly accessible
- Data validation at controller level

### Planned Security Features
- JWT token-based authentication
- Role-based authorization
- Input sanitization
- SQL injection prevention (JPA provides protection)

## Performance Considerations

### Database Performance
- H2 in-memory database for fast development
- JPA query optimization
- Lazy loading configuration
- Connection pooling (default Spring Boot configuration)

### API Performance
- JSON serialization optimization
- Response caching (to be implemented)
- Pagination for large datasets (to be implemented)

## Scalability

### Horizontal Scaling
- Stateless REST API design
- Database clustering support
- Load balancer compatibility

### Vertical Scaling
- JVM tuning parameters
- Memory management
- Database connection pool optimization

## Monitoring and Observability

### Planned Features
- Spring Boot Actuator endpoints
- Health checks
- Metrics collection
- Application logs
- Performance monitoring

## Future Architecture Enhancements

### Microservices Migration
- Course Service
- University Service
- Professor Service
- Matching Service

### Event-Driven Architecture
- Course matching events
- Notification system
- Audit trail

### Caching Layer
- Redis for session management
- Application-level caching
- Database query caching

### Message Queues
- Asynchronous processing
- Email notifications
- Batch operations

## Development Guidelines

### Code Organization
- Package by feature approach
- Clear separation of concerns
- Consistent naming conventions

### Testing Strategy
- Unit tests for service layer
- Integration tests for repositories
- API tests for controllers
- Test data management

### Documentation Standards
- JavaDoc for public APIs
- README files for modules
- Architecture decision records (ADRs)