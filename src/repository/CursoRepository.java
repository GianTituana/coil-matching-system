package com.ejemplo.coilmatching.repository;

import com.ejemplo.coilmatching.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, String> {
    List<Curso> findByIdiomaAndTematica(String idioma, String tematica);
}