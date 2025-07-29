# Development Setup Guide

## Prerequisites

### Required Software

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   # Check Java version
   java -version
   javac -version
   ```
   - Download from: [OpenJDK](https://openjdk.org/) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)

2. **Build Tool** (Choose one)
   - **Maven 3.6+**
     ```bash
     # Check Maven version
     mvn -version
     ```
     Download from: [Apache Maven](https://maven.apache.org/download.cgi)
   
   - **Gradle 7+**
     ```bash
     # Check Gradle version
     gradle -version
     ```
     Download from: [Gradle](https://gradle.org/install/)

3. **Git**
   ```bash
   # Check Git version
   git --version
   ```
   Download from: [Git SCM](https://git-scm.com/downloads)

### Recommended IDE

Choose one of the following IDEs with Spring Boot support:

1. **IntelliJ IDEA** (Recommended)
   - Ultimate Edition includes Spring Boot support
   - Community Edition with Spring plugins

2. **Eclipse IDE for Enterprise Java Developers**
   - Includes Spring Tools 4

3. **Visual Studio Code**
   - Install "Extension Pack for Java"
   - Install "Spring Boot Extension Pack"

## Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/GianTituana/coil-matching-system.git
cd coil-matching-system
```

### 2. Create Build Configuration

Since the project doesn't have a build file yet, create one:

#### Option A: Maven (pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.ejemplo</groupId>
    <artifactId>coil-matching-system</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    
    <name>COIL Matching System</name>
    <description>System for matching university courses in COIL partnerships</description>
    
    <properties>
        <java.version>17</java.version>
        <spring-boot.version>3.2.0</spring-boot.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <sourceDirectory>src</sourceDirectory>
        <resources>
            <resource>
                <directory>resources</directory>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

#### Option B: Gradle (build.gradle)

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.ejemplo'
version = '1.0.0-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

sourceSets {
    main {
        java {
            srcDirs = ['src']
        }
        resources {
            srcDirs = ['resources']
        }
    }
}

tasks.named('test') {
    useJUnitPlatform()
}
```

### 3. Create Main Application Class

Create the main Spring Boot application class:

**File:** `src/CoilMatchingSystemApplication.java`
```java
package com.ejemplo.coilmatching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoilMatchingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoilMatchingSystemApplication.class, args);
    }
}
```

### 4. Update Source Files

Add proper getters and setters to model classes:

#### Update Curso.java
```java
package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;

@Entity
public class Curso {
    @Id
    private String codigo;
    private String nombre;
    private String idioma;
    private String tematica;

    @ManyToOne
    private Universidad universidad;

    // Constructors
    public Curso() {}
    
    public Curso(String codigo, String nombre, String idioma, String tematica) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.tematica = tematica;
    }

    // Getters and Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    
    public String getTematica() { return tematica; }
    public void setTematica(String tematica) { this.tematica = tematica; }
    
    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }
}
```

Similar updates needed for `Profesor.java` and `Universidad.java`.

## Building the Project

### Using Maven
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package application
mvn package

# Run application
mvn spring-boot:run
```

### Using Gradle
```bash
# Clean and build
./gradlew clean build

# Run tests
./gradlew test

# Run application
./gradlew bootRun
```

## Running the Application

### Development Mode

1. **Using IDE**
   - Import project as Maven/Gradle project
   - Run `CoilMatchingSystemApplication.main()` method

2. **Using Command Line**
   ```bash
   # Maven
   mvn spring-boot:run
   
   # Gradle
   ./gradlew bootRun
   
   # JAR
   java -jar target/coil-matching-system-1.0.0-SNAPSHOT.jar
   ```

3. **Access Points**
   - Application: http://localhost:8080
   - API Base: http://localhost:8080/api
   - H2 Console: http://localhost:8080/h2-console

## Database Setup

### H2 Console Access
1. Navigate to: http://localhost:8080/h2-console
2. Connection settings:
   - JDBC URL: `jdbc:h2:mem:coildb`
   - Username: `sa`
   - Password: (leave empty)

### Sample Data Population

Create a data initialization script `resources/data.sql`:

```sql
-- Insert sample universities
INSERT INTO UNIVERSIDAD (nombre, pais) VALUES 
('Universidad Técnica de Berlín', 'Alemania'),
('Universidad de Edimburgo', 'Reino Unido'),
('Universidad de São Paulo', 'Brasil');

-- Insert sample courses
INSERT INTO CURSO (codigo, nombre, idioma, tematica, universidad_nombre) VALUES 
('CS101', 'Introducción a la Programación', 'Español', 'Ciencias de la Computación', 'Universidad de São Paulo'),
('CS102', 'Fundamentos de Programación', 'Inglés', 'Ciencias de la Computación', 'Universidad Técnica de Berlín'),
('MATH201', 'Cálculo Avanzado', 'Inglés', 'Matemáticas', 'Universidad de Edimburgo');

-- Insert sample professors
INSERT INTO PROFESOR (email, nombre, universidad_nombre) VALUES 
('j.silva@usp.br', 'João Silva', 'Universidad de São Paulo'),
('h.mueller@tu-berlin.de', 'Hans Müller', 'Universidad Técnica de Berlín'),
('m.smith@ed.ac.uk', 'Mary Smith', 'Universidad de Edimburgo');
```

## IDE Configuration

### IntelliJ IDEA Setup

1. **Import Project**
   - File → Open → Select project folder
   - Choose Maven or Gradle import

2. **Configure JDK**
   - File → Project Structure → Project → Project SDK → Select JDK 17+

3. **Enable Annotation Processing**
   - Settings → Build → Compiler → Annotation Processors → Enable

4. **Install Useful Plugins**
   - Spring Boot
   - Lombok (if used)
   - Database Navigator

### VS Code Setup

1. **Install Extensions**
   - Extension Pack for Java
   - Spring Boot Extension Pack
   - Rest Client (for API testing)

2. **Configure Settings**
   ```json
   {
     "java.home": "/path/to/jdk-17",
     "spring-boot.ls.logfile": true,
     "java.format.settings.url": "https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml"
   }
   ```

## Testing Setup

### Unit Testing Structure
```
src/
└── test/
    └── java/
        └── com/ejemplo/coilmatching/
            ├── service/
            │   └── SistemaCOILServiceTest.java
            ├── controller/
            │   └── CursoControllerTest.java
            └── repository/
                └── CursoRepositoryTest.java
```

### Sample Test Configuration

**File:** `src/test/resources/application-test.properties`
```properties
# Test database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
logging.level.org.springframework.web=DEBUG
```

## Debugging

### Enable Debug Mode
```bash
# Maven
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Gradle
./gradlew bootRun --debug-jvm

# JAR
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar target/coil-matching-system-*.jar
```

### IDE Debug Configuration
- Set breakpoints in source code
- Connect debugger to port 5005
- Use step-through debugging

## Common Issues and Solutions

### Issue: Port 8080 already in use
**Solution:**
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or change port in application.properties
server.port=8081
```

### Issue: Java version mismatch
**Solution:**
```bash
# Set JAVA_HOME
export JAVA_HOME=/path/to/jdk-17

# Verify Java version
java -version
```

### Issue: H2 console not accessible
**Solution:**
- Verify `spring.h2.console.enabled=true` in application.properties
- Check if application started successfully
- Access http://localhost:8080/h2-console (not localhost:8080/h2)

### Issue: JPA entities not found
**Solution:**
- Ensure @Entity annotations are present
- Check package scanning configuration
- Verify @SpringBootApplication is in root package

## Environment Profiles

### Development Profile
**File:** `resources/application-dev.properties`
```properties
# Development specific settings
spring.jpa.show-sql=true
logging.level.com.ejemplo.coilmatching=DEBUG
spring.h2.console.enabled=true
```

### Production Profile
**File:** `resources/application-prod.properties`
```properties
# Production specific settings
spring.jpa.show-sql=false
logging.level.root=WARN
spring.h2.console.enabled=false
```

### Running with Profiles
```bash
# Development
java -jar app.jar --spring.profiles.active=dev

# Production
java -jar app.jar --spring.profiles.active=prod
```

## Next Steps

1. **Add proper getters/setters to all model classes**
2. **Create build configuration file (pom.xml or build.gradle)**
3. **Add main application class**
4. **Implement proper exception handling**
5. **Add comprehensive unit tests**
6. **Configure logging**
7. **Add API documentation with Swagger**
8. **Implement validation annotations**

## Getting Help

- **Spring Boot Documentation**: https://spring.io/projects/spring-boot
- **Spring Data JPA Guide**: https://spring.io/guides/gs/accessing-data-jpa/
- **H2 Database Documentation**: http://www.h2database.com/html/main.html
- **Project Issues**: Create an issue on GitHub repository