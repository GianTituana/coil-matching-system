package com.ejemplo.coilmatching.controller;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.service.SistemaCOILService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing course-related operations in the COIL matching system.
 * 
 * This controller provides RESTful endpoints for course matching functionality,
 * enabling clients to find compatible courses for COIL partnerships.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    /**
     * Service for handling course matching business logic.
     */
    @Autowired
    private SistemaCOILService sistemaCOILService;

    /**
     * Matches courses based on language and thematic content.
     * 
     * This endpoint accepts a course object with language and theme criteria
     * and returns a list of courses that match these criteria, facilitating
     * the identification of potential COIL partnership opportunities.
     * 
     * @param curso the course object containing matching criteria (language and theme)
     * @return ResponseEntity containing a list of matching courses
     * @throws IllegalArgumentException if the course data is invalid
     * 
     * Example request body:
     * {
     *   "idioma": "English",
     *   "tematica": "Computer Science"
     * }
     */
    @PostMapping("/matching")
    public ResponseEntity<List<Curso>> matchCursos(@RequestBody Curso curso) {
        try {
            List<Curso> cursosCompatibles = sistemaCOILService.realizarMatching(curso);
            return ResponseEntity.ok(cursosCompatibles);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Retrieves all courses in the system.
     * 
     * @return ResponseEntity containing a list of all courses
     */
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        try {
            List<Curso> cursos = sistemaCOILService.obtenerTodosLosCursos();
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Retrieves courses offered by a specific university.
     * 
     * @param nombreUniversidad the name of the university
     * @return ResponseEntity containing a list of courses from the specified university
     */
    @GetMapping("/universidad/{nombreUniversidad}")
    public ResponseEntity<List<Curso>> obtenerCursosPorUniversidad(
            @PathVariable String nombreUniversidad) {
        try {
            List<Curso> cursos = sistemaCOILService.obtenerCursosPorUniversidad(nombreUniversidad);
            return ResponseEntity.ok(cursos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}