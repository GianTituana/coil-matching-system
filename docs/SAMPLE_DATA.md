# Sample Data File

This file contains sample data for the COIL Matching System. 

**Note**: This file has been disabled to prevent startup issues. To enable it, rename it back to `data.sql`.

The sample data includes:
- 5 universities from different countries
- 14 courses across various subjects (Computer Science, Mathematics, Business, Engineering)
- 11 professors with their course assignments

## Usage

To use this sample data:
1. Rename this file to `data.sql`
2. Ensure that your application properties include `spring.sql.init.mode=always`
3. Restart the application

## Alternative: Manual Data Entry

You can also add this data manually through the H2 console at http://localhost:8080/h2-console

## Sample Universities
- Universidad Técnica de Berlín (Germany)
- Universidad de Edimburgo (United Kingdom)
- Universidad de São Paulo (Brazil)  
- Instituto Tecnológico de Monterrey (Mexico)
- Universidad de Sydney (Australia)

## Sample Courses
- Computer Science courses in English and Spanish
- Mathematics courses in English and Spanish
- Business Administration courses  
- Engineering courses in multiple languages

## Purpose

This sample data demonstrates:
- Course matching based on language (idioma) and theme (tematica)
- Multi-university partnerships
- Professor-course relationships
- International diversity in COIL partnerships