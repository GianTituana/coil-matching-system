package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Universidad {
    @Id
    private String nombre;
    private String pais;

    @OneToMany(mappedBy = "universidad")
    private List<Curso> programas;

    // Getters y Setters
}