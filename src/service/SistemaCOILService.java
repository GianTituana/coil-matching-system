package com.ejemplo.coilmatching.service;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SistemaCOILService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> realizarMatching(Curso cursoReferencia) {
        return cursoRepository.findByIdiomaAndTematica(
            cursoReferencia.getIdioma(), cursoReferencia.getTematica()
        );
    }
}