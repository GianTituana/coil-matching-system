# COIL Matching System

Sistema de matching para programas COIL (Collaborative Online International Learning) entre universidades internacionales.

## Descripción

Este sistema permite a universidades encontrar socios internacionales para colaboraciones COIL, facilitando la identificación de cursos compatibles basándose en idioma y temática compartida.

## Características

- **Gestión de Universidades**: CRUD completo para gestionar universidades participantes
- **Gestión de Profesores**: Administración de profesores y sus cursos
- **Gestión de Cursos**: Registro de cursos con información de idioma y temática
- **Matching Inteligente**: Algoritmo que encuentra cursos compatibles basándose en:
  - Idioma común
  - Temática compartida
- **Exportación a Excel**: Descarga de resultados de matching en formato Excel

## Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
  - Spring Web
  - Spring Data JPA
  - Spring Boot Starter Test
- **H2 Database** (base de datos en memoria)
- **Apache POI 5.2.5** (exportación Excel)
- **Maven** (gestión de dependencias)

## Estructura del Proyecto

```
coil-matching-system/
├── src/
│   ├── CoilMatchingSystemApplication.java  # Clase principal
│   ├── controller/                          # Controladores REST
│   │   ├── CursoController.java
│   │   ├── ProfesorController.java
│   │   └── UniversidadController.java
│   ├── model/                               # Entidades JPA
│   │   ├── Curso.java
│   │   ├── Profesor.java
│   │   └── Universidad.java
│   ├── repository/                          # Repositorios Spring Data
│   │   ├── CursoRepository.java
│   │   ├── ProfesorRepository.java
│   │   └── UniversidadRepository.java
│   ├── service/                             # Lógica de negocio
│   │   ├── SistemaCOILService.java
│   │   ├── ProfesorService.java
│   │   └── UniversidadService.java
│   └── export/                              # Utilidades de exportación
│       └── ExcelExporter.java
├── resources/
│   └── application.properties               # Configuración Spring Boot
└── pom.xml                                  # Configuración Maven
```

## Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior

## Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/GianTituana/coil-matching-system.git
cd coil-matching-system
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## API Endpoints

### Universidades

- `GET /api/universidades` - Listar todas las universidades
- `GET /api/universidades/{nombre}` - Obtener universidad por nombre
- `GET /api/universidades/pais/{pais}` - Obtener universidades por país
- `POST /api/universidades` - Crear nueva universidad
- `PUT /api/universidades/{nombre}` - Actualizar universidad
- `DELETE /api/universidades/{nombre}` - Eliminar universidad

### Profesores

- `GET /api/profesores` - Listar todos los profesores
- `GET /api/profesores/{email}` - Obtener profesor por email
- `GET /api/profesores/universidad/{universidadNombre}` - Obtener profesores por universidad
- `POST /api/profesores` - Crear nuevo profesor
- `PUT /api/profesores/{email}` - Actualizar profesor
- `DELETE /api/profesores/{email}` - Eliminar profesor

### Cursos y Matching

- `POST /api/cursos/matching` - Realizar matching de cursos
- `POST /api/cursos/matching/export` - Realizar matching y exportar a Excel

## Ejemplos de Uso

### Crear una Universidad

```bash
curl -X POST http://localhost:8080/api/universidades \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Universidad de Buenos Aires",
    "pais": "Argentina"
  }'
```

### Realizar Matching de Cursos

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

### Exportar Resultados a Excel

```bash
curl -X POST http://localhost:8080/api/cursos/matching/export \
  -H "Content-Type: application/json" \
  -d '{
    "codigo": "CS101",
    "nombre": "Introduction to Computer Science",
    "idioma": "English",
    "tematica": "Computer Science"
  }' \
  --output matching-results.xlsx
```

## Consola H2

La consola H2 está habilitada y disponible en `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:coildb`
- **Usuario**: `sa`
- **Contraseña**: (dejar vacío)

## Testing

Ejecutar los tests:

```bash
mvn test
```

## Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## Contacto

Gian Tituana - [@GianTituana](https://github.com/GianTituana)

Enlace del proyecto: [https://github.com/GianTituana/coil-matching-system](https://github.com/GianTituana/coil-matching-system)
