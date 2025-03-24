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
}