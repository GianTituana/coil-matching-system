package com.ejemplo.coilmatching.controller;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.service.SistemaCOILService;
import com.ejemplo.coilmatching.export.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private SistemaCOILService sistemaCOILService;

    @Autowired
    private ExcelExporter excelExporter;

    @PostMapping("/matching")
    public List<Curso> matchCursos(@RequestBody Curso curso) {
        return sistemaCOILService.realizarMatching(curso);
    }

    @PostMapping("/matching/export")
    public ResponseEntity<byte[]> matchAndExportCursos(@RequestBody Curso curso) throws IOException {
        List<Curso> resultados = sistemaCOILService.realizarMatching(curso);
        byte[] excelData = excelExporter.exportarMatchingResultados(resultados);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "matching-results.xlsx");

        return ResponseEntity.ok()
            .headers(headers)
            .body(excelData);
    }
}
