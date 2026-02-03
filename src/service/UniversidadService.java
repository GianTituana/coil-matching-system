package com.ejemplo.coilmatching.service;

import com.ejemplo.coilmatching.model.Universidad;
import com.ejemplo.coilmatching.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversidadService {

    @Autowired
    private UniversidadRepository universidadRepository;

    public List<Universidad> obtenerTodas() {
        return universidadRepository.findAll();
    }

    public Optional<Universidad> obtenerPorNombre(String nombre) {
        return universidadRepository.findById(nombre);
    }

    public List<Universidad> obtenerPorPais(String pais) {
        return universidadRepository.findByPais(pais);
    }

    public Universidad guardar(Universidad universidad) {
        return universidadRepository.save(universidad);
    }

    public void eliminar(String nombre) {
        universidadRepository.deleteById(nombre);
    }
}
