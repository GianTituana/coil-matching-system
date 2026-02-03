package com.ejemplo.coilmatching.service;

import com.ejemplo.coilmatching.model.Profesor;
import com.ejemplo.coilmatching.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerPorEmail(String email) {
        return profesorRepository.findById(email);
    }

    public List<Profesor> obtenerPorUniversidad(String universidadNombre) {
        return profesorRepository.findByUniversidadNombre(universidadNombre);
    }

    public Profesor guardar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void eliminar(String email) {
        profesorRepository.deleteById(email);
    }
}
