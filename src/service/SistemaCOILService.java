package com.ejemplo.coilmatching.service;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that implements the core business logic for the COIL matching system.
 * 
 * This service provides functionality to match courses based on language and
 * thematic content to facilitate COIL partnerships between universities.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@Service
public class SistemaCOILService {

    /**
     * Repository for accessing course data.
     */
    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Performs course matching based on language and thematic content.
     * 
     * This method finds all courses that match the language and thematic
     * content of the provided reference course, enabling universities to
     * identify potential COIL partnership opportunities.
     * 
     * @param cursoReferencia the reference course containing matching criteria
     * @return a list of courses that match the specified language and theme
     * @throws IllegalArgumentException if the reference course is null or
     *         if language or theme are null or empty
     */
    public List<Curso> realizarMatching(Curso cursoReferencia) {
        if (cursoReferencia == null) {
            throw new IllegalArgumentException("Reference course cannot be null");
        }
        
        String idioma = cursoReferencia.getIdioma();
        String tematica = cursoReferencia.getTematica();
        
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new IllegalArgumentException("Language (idioma) cannot be null or empty");
        }
        
        if (tematica == null || tematica.trim().isEmpty()) {
            throw new IllegalArgumentException("Theme (tematica) cannot be null or empty");
        }
        
        return cursoRepository.findByIdiomaAndTematica(idioma, tematica);
    }
    
    /**
     * Finds all courses offered by a specific university.
     * 
     * @param nombreUniversidad the name of the university
     * @return a list of courses offered by the university
     * @throws IllegalArgumentException if university name is null or empty
     */
    public List<Curso> obtenerCursosPorUniversidad(String nombreUniversidad) {
        if (nombreUniversidad == null || nombreUniversidad.trim().isEmpty()) {
            throw new IllegalArgumentException("University name cannot be null or empty");
        }
        
        return cursoRepository.findByUniversidadNombre(nombreUniversidad);
    }
    
    /**
     * Gets all courses in the system.
     * 
     * @return a list of all courses
     */
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }
}