# Database Schema Documentation

## Overview

The COIL Matching System uses a relational database schema designed to support university course matching for international collaboration. The schema includes entities for universities, courses, and professors with their relationships.

## Database Configuration

### Development Environment
- **Database**: H2 (In-memory)
- **JDBC URL**: `jdbc:h2:mem:coildb`
- **Username**: `sa`
- **Password**: (empty)
- **Console**: http://localhost:8080/h2-console

### Production Environment
- **Database**: PostgreSQL/MySQL (recommended)
- **Connection**: Configured via `application.properties`

## Entity Relationship Diagram

```
                    ┌─────────────────────────────────────┐
                    │             UNIVERSIDAD             │
                    │─────────────────────────────────────│
                    │ nombre VARCHAR(255) PK              │
                    │ pais VARCHAR(255)                   │
                    └─────────────────┬───────────────────┘
                                      │
                                      │ 1:N
                                      │
                    ┌─────────────────▼───────────────────┐
                    │               CURSO                 │
                    │─────────────────────────────────────│
                    │ codigo VARCHAR(255) PK              │
                    │ nombre VARCHAR(255)                 │
                    │ idioma VARCHAR(255)                 │
                    │ tematica VARCHAR(255)               │
                    │ universidad_nombre VARCHAR(255) FK  │
                    └─────────────────┬───────────────────┘
                                      │
                                      │ M:N
                                      │
                    ┌─────────────────▼───────────────────┐
                    │            PROFESOR_CURSO           │
                    │─────────────────────────────────────│
                    │ profesor_email VARCHAR(255) FK      │
                    │ curso_codigo VARCHAR(255) FK        │
                    └─────────────────┬───────────────────┘
                                      │
                                      │ M:N
                                      │
                    ┌─────────────────▼───────────────────┐
                    │             PROFESOR                │
                    │─────────────────────────────────────│
                    │ email VARCHAR(255) PK               │
                    │ nombre VARCHAR(255)                 │
                    │ universidad_nombre VARCHAR(255) FK  │
                    └─────────────────────────────────────┘
```

## Table Definitions

### UNIVERSIDAD (University)

Stores information about participating universities.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| nombre | VARCHAR(255) | PRIMARY KEY, NOT NULL | University name (unique identifier) |
| pais | VARCHAR(255) | NOT NULL | Country where university is located |

**Indexes:**
- Primary Key: `PK_UNIVERSIDAD(nombre)`
- Index: `IDX_UNIVERSIDAD_PAIS(pais)` - for country-based queries

**Sample Data:**
```sql
INSERT INTO UNIVERSIDAD (nombre, pais) VALUES 
('Universidad Técnica de Berlín', 'Alemania'),
('Universidad de Edimburgo', 'Reino Unido'),
('Universidad de São Paulo', 'Brasil');
```

### CURSO (Course)

Stores course information and relationships to universities.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| codigo | VARCHAR(255) | PRIMARY KEY, NOT NULL | Unique course identifier |
| nombre | VARCHAR(255) | NOT NULL | Course name/title |
| idioma | VARCHAR(255) | NOT NULL | Language of instruction |
| tematica | VARCHAR(255) | NOT NULL | Subject theme/topic |
| universidad_nombre | VARCHAR(255) | FOREIGN KEY, NOT NULL | Reference to UNIVERSIDAD.nombre |

**Relationships:**
- **Many-to-One** with UNIVERSIDAD
- **Many-to-Many** with PROFESOR (via PROFESOR_CURSO)

**Indexes:**
- Primary Key: `PK_CURSO(codigo)`
- Foreign Key: `FK_CURSO_UNIVERSIDAD(universidad_nombre)`
- Composite Index: `IDX_CURSO_MATCHING(idioma, tematica)` - for matching queries
- Index: `IDX_CURSO_UNIVERSIDAD(universidad_nombre)` - for university queries

**Sample Data:**
```sql
INSERT INTO CURSO (codigo, nombre, idioma, tematica, universidad_nombre) VALUES 
('CS101', 'Introducción a la Programación', 'Español', 'Ciencias de la Computación', 'Universidad de São Paulo'),
('CS102', 'Fundamentos de Programación', 'Inglés', 'Ciencias de la Computación', 'Universidad Técnica de Berlín'),
('MATH201', 'Cálculo Avanzado', 'Inglés', 'Matemáticas', 'Universidad de Edimburgo');
```

### PROFESOR (Professor)

Stores professor information and university affiliation.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| email | VARCHAR(255) | PRIMARY KEY, NOT NULL | Professor's email (unique identifier) |
| nombre | VARCHAR(255) | NOT NULL | Professor's full name |
| universidad_nombre | VARCHAR(255) | FOREIGN KEY, NOT NULL | Reference to UNIVERSIDAD.nombre |

**Relationships:**
- **Many-to-One** with UNIVERSIDAD
- **Many-to-Many** with CURSO (via PROFESOR_CURSO)

**Indexes:**
- Primary Key: `PK_PROFESOR(email)`
- Foreign Key: `FK_PROFESOR_UNIVERSIDAD(universidad_nombre)`
- Index: `IDX_PROFESOR_NOMBRE(nombre)` - for name-based searches

**Sample Data:**
```sql
INSERT INTO PROFESOR (email, nombre, universidad_nombre) VALUES 
('j.silva@usp.br', 'João Silva', 'Universidad de São Paulo'),
('h.mueller@tu-berlin.de', 'Hans Müller', 'Universidad Técnica de Berlín'),
('m.smith@ed.ac.uk', 'Mary Smith', 'Universidad de Edimburgo');
```

### PROFESOR_CURSO (Join Table)

Junction table for Many-to-Many relationship between professors and courses.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| profesor_email | VARCHAR(255) | FOREIGN KEY, NOT NULL | Reference to PROFESOR.email |
| curso_codigo | VARCHAR(255) | FOREIGN KEY, NOT NULL | Reference to CURSO.codigo |

**Relationships:**
- **Many-to-One** with PROFESOR
- **Many-to-One** with CURSO

**Indexes:**
- Composite Primary Key: `PK_PROFESOR_CURSO(profesor_email, curso_codigo)`
- Foreign Key: `FK_PROFESOR_CURSO_PROFESOR(profesor_email)`
- Foreign Key: `FK_PROFESOR_CURSO_CURSO(curso_codigo)`

**Sample Data:**
```sql
INSERT INTO PROFESOR_CURSO (profesor_email, curso_codigo) VALUES 
('j.silva@usp.br', 'CS101'),
('h.mueller@tu-berlin.de', 'CS102'),
('m.smith@ed.ac.uk', 'MATH201');
```

## DDL Scripts

### Table Creation

```sql
-- Create Universidad table
CREATE TABLE UNIVERSIDAD (
    nombre VARCHAR(255) NOT NULL,
    pais VARCHAR(255) NOT NULL,
    CONSTRAINT PK_UNIVERSIDAD PRIMARY KEY (nombre)
);

-- Create Curso table
CREATE TABLE CURSO (
    codigo VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    idioma VARCHAR(255) NOT NULL,
    tematica VARCHAR(255) NOT NULL,
    universidad_nombre VARCHAR(255) NOT NULL,
    CONSTRAINT PK_CURSO PRIMARY KEY (codigo),
    CONSTRAINT FK_CURSO_UNIVERSIDAD 
        FOREIGN KEY (universidad_nombre) 
        REFERENCES UNIVERSIDAD(nombre)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Create Profesor table
CREATE TABLE PROFESOR (
    email VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    universidad_nombre VARCHAR(255) NOT NULL,
    CONSTRAINT PK_PROFESOR PRIMARY KEY (email),
    CONSTRAINT FK_PROFESOR_UNIVERSIDAD 
        FOREIGN KEY (universidad_nombre) 
        REFERENCES UNIVERSIDAD(nombre)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Create Profesor_Curso junction table
CREATE TABLE PROFESOR_CURSO (
    profesor_email VARCHAR(255) NOT NULL,
    curso_codigo VARCHAR(255) NOT NULL,
    CONSTRAINT PK_PROFESOR_CURSO PRIMARY KEY (profesor_email, curso_codigo),
    CONSTRAINT FK_PROFESOR_CURSO_PROFESOR 
        FOREIGN KEY (profesor_email) 
        REFERENCES PROFESOR(email)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FK_PROFESOR_CURSO_CURSO 
        FOREIGN KEY (curso_codigo) 
        REFERENCES CURSO(codigo)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
```

### Index Creation

```sql
-- Performance indexes for common queries
CREATE INDEX IDX_UNIVERSIDAD_PAIS ON UNIVERSIDAD(pais);
CREATE INDEX IDX_CURSO_MATCHING ON CURSO(idioma, tematica);
CREATE INDEX IDX_CURSO_UNIVERSIDAD ON CURSO(universidad_nombre);
CREATE INDEX IDX_PROFESOR_NOMBRE ON PROFESOR(nombre);
CREATE INDEX IDX_PROFESOR_UNIVERSIDAD ON PROFESOR(universidad_nombre);
```

## Common Queries

### Course Matching Query

```sql
-- Find courses matching language and theme
SELECT c.*, u.nombre as universidad_nombre, u.pais
FROM CURSO c
JOIN UNIVERSIDAD u ON c.universidad_nombre = u.nombre
WHERE c.idioma = ? AND c.tematica = ?
ORDER BY u.pais, c.nombre;
```

### University Courses Query

```sql
-- Get all courses for a specific university
SELECT c.*
FROM CURSO c
WHERE c.universidad_nombre = ?
ORDER BY c.codigo;
```

### Professor Courses Query

```sql
-- Get all courses taught by a professor
SELECT c.*, u.nombre as universidad_nombre
FROM CURSO c
JOIN PROFESOR_CURSO pc ON c.codigo = pc.curso_codigo
JOIN UNIVERSIDAD u ON c.universidad_nombre = u.nombre
WHERE pc.profesor_email = ?
ORDER BY c.codigo;
```

### University Statistics Query

```sql
-- Get course count by university and language
SELECT 
    u.nombre as universidad,
    u.pais,
    c.idioma,
    COUNT(*) as curso_count
FROM UNIVERSIDAD u
JOIN CURSO c ON u.nombre = c.universidad_nombre
GROUP BY u.nombre, u.pais, c.idioma
ORDER BY u.nombre, c.idioma;
```

## Data Validation Rules

### Business Rules

1. **Universidad (University)**
   - University name must be unique across the system
   - Country name should follow ISO 3166 standards (recommended)

2. **Curso (Course)**
   - Course code must be unique across the system
   - Language should use ISO 639 language codes (recommended)
   - Theme categories should be standardized (future enhancement)

3. **Profesor (Professor)**
   - Email must be unique and valid format
   - Professor must be associated with exactly one university

4. **Profesor_Curso (Professor-Course)**
   - Professor can teach multiple courses
   - Course can be taught by multiple professors
   - No duplicate assignments (enforced by composite primary key)

### Data Integrity Constraints

```sql
-- Email format validation (PostgreSQL example)
ALTER TABLE PROFESOR ADD CONSTRAINT chk_email_format 
    CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$');

-- Non-empty string constraints
ALTER TABLE UNIVERSIDAD ADD CONSTRAINT chk_nombre_not_empty 
    CHECK (LENGTH(TRIM(nombre)) > 0);
    
ALTER TABLE UNIVERSIDAD ADD CONSTRAINT chk_pais_not_empty 
    CHECK (LENGTH(TRIM(pais)) > 0);

-- Course validation
ALTER TABLE CURSO ADD CONSTRAINT chk_idioma_not_empty 
    CHECK (LENGTH(TRIM(idioma)) > 0);
    
ALTER TABLE CURSO ADD CONSTRAINT chk_tematica_not_empty 
    CHECK (LENGTH(TRIM(tematica)) > 0);
```

## Migration Scripts

### Version 1.0.0 - Initial Schema

```sql
-- V1.0.0__Initial_schema.sql
-- Create all tables and initial indexes
-- (Include DDL scripts from above)
```

### Future Migrations

```sql
-- V1.1.0__Add_course_details.sql
ALTER TABLE CURSO ADD COLUMN duracion_semanas INTEGER;
ALTER TABLE CURSO ADD COLUMN nivel VARCHAR(50);
ALTER TABLE CURSO ADD COLUMN descripcion TEXT;

-- V1.2.0__Add_audit_fields.sql
ALTER TABLE CURSO ADD COLUMN fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE CURSO ADD COLUMN fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE UNIVERSIDAD ADD COLUMN fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE PROFESOR ADD COLUMN fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
```

## Performance Considerations

### Query Optimization

1. **Matching Queries**
   - Composite index on (idioma, tematica) for fast course matching
   - Consider materialized views for complex matching scenarios

2. **University Queries**
   - Index on universidad_nombre in CURSO table
   - Consider partitioning by country for large datasets

3. **Professor Queries**
   - Index on profesor_email in junction table
   - Consider denormalization for read-heavy scenarios

### Scaling Strategies

1. **Read Replicas**
   - Separate read replicas for reporting queries
   - Master-slave configuration for write/read separation

2. **Partitioning**
   - Partition CURSO table by universidad_nombre
   - Partition by country for geographically distributed data

3. **Caching**
   - Redis cache for frequently accessed matching results
   - Application-level caching for university data

## Backup and Recovery

### Backup Strategy

```sql
-- Daily backup script
pg_dump -h localhost -U username -d coildb > backup_$(date +%Y%m%d).sql

-- Point-in-time recovery setup
-- Enable WAL archiving in postgresql.conf
```

### Recovery Procedures

1. **Full Recovery**
   ```sql
   createdb coildb_recovery
   psql -d coildb_recovery -f backup_20240115.sql
   ```

2. **Partial Recovery**
   ```sql
   -- Restore specific tables only
   pg_restore -t universidad -t curso backup_file.dump
   ```

## Security Considerations

### Database Security

1. **Access Control**
   - Separate users for application and admin access
   - Role-based permissions
   - No direct database access from external networks

2. **Data Protection**
   - Encrypt sensitive data at rest
   - Use SSL/TLS for connections
   - Regular security audits

3. **SQL Injection Prevention**
   - Use parameterized queries (JPA handles this)
   - Input validation at application layer
   - Database user with minimal privileges

## Monitoring and Maintenance

### Performance Monitoring

1. **Key Metrics**
   - Query execution times
   - Index usage statistics
   - Connection pool utilization
   - Database size growth

2. **Monitoring Queries**
   ```sql
   -- Check slow queries (PostgreSQL)
   SELECT query, mean_time, calls, total_time
   FROM pg_stat_statements
   ORDER BY mean_time DESC LIMIT 10;
   
   -- Check index usage
   SELECT schemaname, tablename, indexname, idx_scan, idx_tup_read, idx_tup_fetch
   FROM pg_stat_user_indexes
   ORDER BY idx_scan DESC;
   ```

### Maintenance Tasks

1. **Regular Tasks**
   - VACUUM and ANALYZE (PostgreSQL)
   - Index rebuilding if needed
   - Statistics updates
   - Log rotation

2. **Data Cleanup**
   - Archive old data
   - Remove orphaned records
   - Update statistics