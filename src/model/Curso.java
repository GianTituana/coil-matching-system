package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;

/**
 * Entity representing a university course in the COIL matching system.
 * 
 * This entity stores information about courses offered by universities,
 * including the language of instruction and thematic content which are
 * used for matching with other courses in COIL partnerships.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@Entity
public class Curso {
    
    /**
     * Unique course identifier code.
     * This serves as the primary key for the course entity.
     */
    @Id
    private String codigo;
    
    /**
     * The name or title of the course.
     */
    private String nombre;
    
    /**
     * The language of instruction for the course.
     * Used as a matching criterion for COIL partnerships.
     */
    private String idioma;
    
    /**
     * The thematic content or subject area of the course.
     * Used as a matching criterion for COIL partnerships.
     */
    private String tematica;

    /**
     * The university that offers this course.
     * Many courses can belong to one university.
     */
    @ManyToOne
    private Universidad universidad;

    /**
     * Default constructor for JPA.
     */
    public Curso() {}
    
    /**
     * Constructs a new Curso with the specified details.
     * 
     * @param codigo the unique course code
     * @param nombre the course name
     * @param idioma the language of instruction
     * @param tematica the thematic content
     */
    public Curso(String codigo, String nombre, String idioma, String tematica) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.tematica = tematica;
    }

    // Getters and Setters
    
    /**
     * Gets the course code.
     * 
     * @return the unique course identifier
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the course code.
     * 
     * @param codigo the unique course identifier
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the course name.
     * 
     * @return the course name or title
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the course name.
     * 
     * @param nombre the course name or title
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the language of instruction.
     * 
     * @return the language code or name
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Sets the language of instruction.
     * 
     * @param idioma the language code or name
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Gets the thematic content.
     * 
     * @return the subject theme or topic
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Sets the thematic content.
     * 
     * @param tematica the subject theme or topic
     */
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    /**
     * Gets the associated university.
     * 
     * @return the university that offers this course
     */
    public Universidad getUniversidad() {
        return universidad;
    }

    /**
     * Sets the associated university.
     * 
     * @param universidad the university that offers this course
     */
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idioma='" + idioma + '\'' +
                ", tematica='" + tematica + '\'' +
                '}';
    }
}