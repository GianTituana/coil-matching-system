package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Profesor {
    @Id
    private String email;
    private String nombre;

    @ManyToOne
    private Universidad universidad;

    @ManyToMany
    private List<Curso> cursos;

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}