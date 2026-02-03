package com.ejemplo.coilmatching.service;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SistemaCOILServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private SistemaCOILService sistemaCOILService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarMatching() {
        // Arrange
        Curso cursoReferencia = new Curso();
        cursoReferencia.setCodigo("CS101");
        cursoReferencia.setIdioma("English");
        cursoReferencia.setTematica("Computer Science");

        Curso cursoMatch1 = new Curso();
        cursoMatch1.setCodigo("CS102");
        cursoMatch1.setIdioma("English");
        cursoMatch1.setTematica("Computer Science");

        Curso cursoMatch2 = new Curso();
        cursoMatch2.setCodigo("CS103");
        cursoMatch2.setIdioma("English");
        cursoMatch2.setTematica("Computer Science");

        List<Curso> cursosEsperados = Arrays.asList(cursoMatch1, cursoMatch2);

        when(cursoRepository.findByIdiomaAndTematica("English", "Computer Science"))
            .thenReturn(cursosEsperados);

        // Act
        List<Curso> resultado = sistemaCOILService.realizarMatching(cursoReferencia);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(cursoRepository, times(1))
            .findByIdiomaAndTematica("English", "Computer Science");
    }

    @Test
    void testRealizarMatchingSinResultados() {
        // Arrange
        Curso cursoReferencia = new Curso();
        cursoReferencia.setIdioma("French");
        cursoReferencia.setTematica("Art History");

        when(cursoRepository.findByIdiomaAndTematica("French", "Art History"))
            .thenReturn(Arrays.asList());

        // Act
        List<Curso> resultado = sistemaCOILService.realizarMatching(cursoReferencia);

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }
}
