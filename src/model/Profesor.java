package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity representing a professor in the COIL matching system.
 * 
 * This entity stores information about professors who teach courses
 * at universities and participate in COIL partnerships.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@Entity
public class Profesor {
    
    /**
     * Professor's email address, used as the unique identifier.
     * This serves as the primary key for the professor entity.
     */
    @Id
    private String email;
    
    /**
     * The full name of the professor.
     */
    private String nombre;

    /**
     * The university where the professor works.
     * Many professors can work at one university.
     */
    @ManyToOne
    private Universidad universidad;

    /**
     * The courses taught by this professor.
     * Professors can teach multiple courses, and courses can be taught by multiple professors.
     */
    @ManyToMany
    private List<Curso> cursos;

    /**
     * Default constructor for JPA.
     */
    public Profesor() {}
    
    /**
     * Constructs a new Profesor with the specified details.
     * 
     * @param email the professor's email address
     * @param nombre the professor's full name
     */
    public Profesor(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    // Getters and Setters
    
    /**
     * Gets the professor's email address.
     * 
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the professor's email address.
     * 
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the professor's name.
     * 
     * @return the full name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the professor's name.
     * 
     * @param nombre the full name
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the associated university.
     * 
     * @return the university where the professor works
     */
    public Universidad getUniversidad() {
        return universidad;
    }

    /**
     * Sets the associated university.
     * 
     * @param universidad the university where the professor works
     */
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Gets the list of courses taught by this professor.
     * 
     * @return the list of courses
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * Sets the list of courses taught by this professor.
     * 
     * @param cursos the list of courses
     */
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}