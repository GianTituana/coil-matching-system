package com.ejemplo.coilmatching.repository;

import com.ejemplo.coilmatching.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing course data in the COIL matching system.
 * 
 * This repository provides data access methods for the Curso entity,
 * including custom query methods for course matching functionality.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {
    
    /**
     * Finds courses that match the specified language and thematic content.
     * 
     * This method is used for the core matching functionality of the COIL system,
     * identifying courses that could be suitable for international partnerships
     * based on language and subject matter compatibility.
     * 
     * @param idioma the language of instruction
     * @param tematica the thematic content or subject area
     * @return a list of courses matching the specified criteria
     */
    List<Curso> findByIdiomaAndTematica(String idioma, String tematica);
    
    /**
     * Finds all courses offered by a specific university.
     * 
     * @param nombreUniversidad the name of the university
     * @return a list of courses offered by the specified university
     */
    List<Curso> findByUniversidadNombre(String nombreUniversidad);
    
    /**
     * Finds courses by language only.
     * 
     * @param idioma the language of instruction
     * @return a list of courses taught in the specified language
     */
    List<Curso> findByIdioma(String idioma);
    
    /**
     * Finds courses by thematic content only.
     * 
     * @param tematica the thematic content or subject area
     * @return a list of courses in the specified subject area
     */
    List<Curso> findByTematica(String tematica);
    
    /**
     * Custom query to find courses with case-insensitive matching.
     * 
     * @param idioma the language of instruction (case-insensitive)
     * @param tematica the thematic content (case-insensitive)
     * @return a list of courses matching the criteria
     */
    @Query("SELECT c FROM Curso c WHERE LOWER(c.idioma) = LOWER(:idioma) AND LOWER(c.tematica) = LOWER(:tematica)")
    List<Curso> findByIdiomaAndTematicaIgnoreCase(@Param("idioma") String idioma, @Param("tematica") String tematica);
}