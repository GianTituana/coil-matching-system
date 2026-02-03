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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Curso> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Curso> programas) {
        this.programas = programas;
    }
}