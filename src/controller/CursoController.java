package com.ejemplo.coilmatching.controller;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.service.SistemaCOILService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private SistemaCOILService sistemaCOILService;

    @PostMapping("/matching")
    public List<Curso> matchCursos(@RequestBody Curso curso) {
        return sistemaCOILService.realizarMatching(curso);
    }
}