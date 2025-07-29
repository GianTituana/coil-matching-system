package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity representing a university in the COIL matching system.
 * 
 * This entity stores information about universities that participate
 * in COIL partnerships and offer courses for international collaboration.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@Entity
public class Universidad {
    
    /**
     * The name of the university, used as the unique identifier.
     * This serves as the primary key for the university entity.
     */
    @Id
    private String nombre;
    
    /**
     * The country where the university is located.
     */
    private String pais;

    /**
     * The courses offered by this university.
     * One university can offer multiple courses.
     */
    @OneToMany(mappedBy = "universidad")
    private List<Curso> programas;

    /**
     * Default constructor for JPA.
     */
    public Universidad() {}
    
    /**
     * Constructs a new Universidad with the specified details.
     * 
     * @param nombre the university name
     * @param pais the country where the university is located
     */
    public Universidad(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters and Setters
    
    /**
     * Gets the university name.
     * 
     * @return the name of the university
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the university name.
     * 
     * @param nombre the name of the university
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the country where the university is located.
     * 
     * @return the country name
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the country where the university is located.
     * 
     * @param pais the country name
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the list of courses offered by this university.
     * 
     * @return the list of courses/programs
     */
    public List<Curso> getProgramas() {
        return programas;
    }

    /**
     * Sets the list of courses offered by this university.
     * 
     * @param programas the list of courses/programs
     */
    public void setProgramas(List<Curso> programas) {
        this.programas = programas;
    }

    @Override
    public String toString() {
        return "Universidad{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}