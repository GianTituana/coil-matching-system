package com.ejemplo.coilmatching.repository;

import com.ejemplo.coilmatching.model.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversidadRepository extends JpaRepository<Universidad, String> {
    List<Universidad> findByPais(String pais);
}
