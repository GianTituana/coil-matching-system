package com.ejemplo.coilmatching.controller;

import com.ejemplo.coilmatching.model.Profesor;
import com.ejemplo.coilmatching.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public List<Profesor> listarTodos() {
        return profesorService.obtenerTodos();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Profesor> obtenerPorEmail(@PathVariable String email) {
        return profesorService.obtenerPorEmail(email)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/universidad/{universidadNombre}")
    public List<Profesor> obtenerPorUniversidad(@PathVariable String universidadNombre) {
        return profesorService.obtenerPorUniversidad(universidadNombre);
    }

    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorService.guardar(profesor);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Profesor> actualizar(
            @PathVariable String email, 
            @RequestBody Profesor profesor) {
        if (!profesorService.obtenerPorEmail(email).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profesor.setEmail(email);
        return ResponseEntity.ok(profesorService.guardar(profesor));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> eliminar(@PathVariable String email) {
        if (!profesorService.obtenerPorEmail(email).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profesorService.eliminar(email);
        return ResponseEntity.noContent().build();
    }
}
