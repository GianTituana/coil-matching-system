package com.ejemplo.coilmatching.repository;

import com.ejemplo.coilmatching.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    List<Profesor> findByUniversidadNombre(String universidadNombre);
}
