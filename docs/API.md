# API Documentation

## Overview

The COIL Matching System provides a RESTful API for managing universities, courses, professors, and performing course matching operations.

## Base URL

```
http://localhost:8080/api
```

## Authentication

Currently, the API does not require authentication. This will be added in future versions.

## Content Type

All requests and responses use `application/json` content type.

## Endpoints

### Course Management

#### Match Courses

Find courses that match the given course criteria based on language and thematic content.

**Endpoint:** `POST /api/cursos/matching`

**Description:** Returns a list of courses that match the provided course's language and thematic content.

**Request Body:**
```json
{
  "codigo": "string",       // Course code (optional for matching)
  "nombre": "string",       // Course name (optional for matching)
  "idioma": "string",       // Language of instruction (required for matching)
  "tematica": "string"      // Subject theme (required for matching)
}
```

**Response:**
```json
[
  {
    "codigo": "string",
    "nombre": "string",
    "idioma": "string",
    "tematica": "string",
    "universidad": {
      "nombre": "string",
      "pais": "string"
    }
  }
]
```

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/cursos/matching \
  -H "Content-Type: application/json" \
  -d '{
    "codigo": "CS101",
    "nombre": "Introduction to Computer Science",
    "idioma": "English",
    "tematica": "Computer Science"
  }'
```

**Example Response:**
```json
[
  {
    "codigo": "CS102",
    "nombre": "Programming Fundamentals",
    "idioma": "English",
    "tematica": "Computer Science",
    "universidad": {
      "nombre": "Technical University of Berlin",
      "pais": "Germany"
    }
  },
  {
    "codigo": "COMP150",
    "nombre": "Introduction to Programming",
    "idioma": "English",
    "tematica": "Computer Science",
    "universidad": {
      "nombre": "University of Edinburgh",
      "pais": "United Kingdom"
    }
  }
]
```

**Status Codes:**
- `200 OK` - Successful matching operation
- `400 Bad Request` - Invalid request body or missing required fields
- `500 Internal Server Error` - Server error during processing

## Data Models

### Curso (Course)

```json
{
  "codigo": "string",       // Unique course identifier
  "nombre": "string",       // Course name
  "idioma": "string",       // Language of instruction
  "tematica": "string",     // Subject theme/topic
  "universidad": {          // Associated university
    "nombre": "string",
    "pais": "string"
  }
}
```

### Universidad (University)

```json
{
  "nombre": "string",       // University name (Primary Key)
  "pais": "string",         // Country where university is located
  "programas": [            // List of courses offered
    {
      "codigo": "string",
      "nombre": "string",
      "idioma": "string",
      "tematica": "string"
    }
  ]
}
```

### Profesor (Professor)

```json
{
  "email": "string",        // Professor email (Primary Key)
  "nombre": "string",       // Professor name
  "universidad": {          // Associated university
    "nombre": "string",
    "pais": "string"
  },
  "cursos": [               // Courses taught by professor
    {
      "codigo": "string",
      "nombre": "string",
      "idioma": "string",
      "tematica": "string"
    }
  ]
}
```

## Error Handling

### Error Response Format

```json
{
  "timestamp": "2024-01-15T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for object='curso'. Error count: 2",
  "path": "/api/cursos/matching"
}
```

### Common Error Codes

| Status Code | Description |
|-------------|-------------|
| 400 | Bad Request - Invalid input data |
| 404 | Not Found - Resource not found |
| 405 | Method Not Allowed - HTTP method not supported |
| 500 | Internal Server Error - Server processing error |

## Validation Rules

### Course Matching Request

- `idioma` (language): Required, non-empty string
- `tematica` (theme): Required, non-empty string
- `codigo` (code): Optional, must be unique if provided
- `nombre` (name): Optional string

## Rate Limiting

Currently, no rate limiting is implemented. This may be added in future versions.

## Versioning

The API currently does not implement versioning. Future versions will use semantic versioning in the URL path (e.g., `/api/v1/`).

## Future Endpoints

The following endpoints are planned for future releases:

### University Management
- `GET /api/universidades` - List all universities
- `POST /api/universidades` - Create new university
- `GET /api/universidades/{nombre}` - Get university details
- `PUT /api/universidades/{nombre}` - Update university
- `DELETE /api/universidades/{nombre}` - Delete university

### Course Management
- `GET /api/cursos` - List all courses
- `POST /api/cursos` - Create new course
- `GET /api/cursos/{codigo}` - Get course details
- `PUT /api/cursos/{codigo}` - Update course
- `DELETE /api/cursos/{codigo}` - Delete course

### Professor Management
- `GET /api/profesores` - List all professors
- `POST /api/profesores` - Create new professor
- `GET /api/profesores/{email}` - Get professor details
- `PUT /api/profesores/{email}` - Update professor
- `DELETE /api/profesores/{email}` - Delete professor

### Advanced Matching
- `POST /api/matching/advanced` - Advanced course matching with multiple criteria
- `GET /api/matching/history` - Get matching history
- `POST /api/matching/save` - Save matching results

## Testing

### Using curl

```bash
# Test course matching
curl -X POST http://localhost:8080/api/cursos/matching \
  -H "Content-Type: application/json" \
  -d '{
    "idioma": "Spanish",
    "tematica": "Mathematics"
  }'
```

### Using Postman

1. Create a new POST request to `http://localhost:8080/api/cursos/matching`
2. Set Content-Type header to `application/json`
3. Add request body with course data
4. Send request and verify response

### Integration Testing

For automated testing, use the following test cases:

1. Valid matching request with existing courses
2. Invalid request with missing required fields
3. Request with non-existent language/theme combination
4. Large dataset performance testing

## Support

For API-related questions or issues:
- Check the application logs for detailed error information
- Verify H2 console at `http://localhost:8080/h2-console` for data issues
- Create an issue on GitHub with API request/response details