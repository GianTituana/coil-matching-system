package com.ejemplo.coilmatching.controller;

import com.ejemplo.coilmatching.model.Universidad;
import com.ejemplo.coilmatching.service.UniversidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universidades")
public class UniversidadController {

    @Autowired
    private UniversidadService universidadService;

    @GetMapping
    public List<Universidad> listarTodas() {
        return universidadService.obtenerTodas();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Universidad> obtenerPorNombre(@PathVariable String nombre) {
        return universidadService.obtenerPorNombre(nombre)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pais/{pais}")
    public List<Universidad> obtenerPorPais(@PathVariable String pais) {
        return universidadService.obtenerPorPais(pais);
    }

    @PostMapping
    public Universidad crear(@RequestBody Universidad universidad) {
        return universidadService.guardar(universidad);
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<Universidad> actualizar(
            @PathVariable String nombre, 
            @RequestBody Universidad universidad) {
        if (!universidadService.obtenerPorNombre(nombre).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        universidad.setNombre(nombre);
        return ResponseEntity.ok(universidadService.guardar(universidad));
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> eliminar(@PathVariable String nombre) {
        if (!universidadService.obtenerPorNombre(nombre).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        universidadService.eliminar(nombre);
        return ResponseEntity.noContent().build();
    }
}
