package com.ejemplo.coilmatching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the COIL Matching System.
 * 
 * This class serves as the entry point for the Spring Boot application,
 * providing course matching functionality for Collaborative Online 
 * International Learning (COIL) partnerships between universities.
 * 
 * The application enables universities to find compatible courses
 * based on language and thematic content for international collaboration.
 * 
 * @author COIL Matching System
 * @version 1.0
 */
@SpringBootApplication
public class CoilMatchingSystemApplication {

    /**
     * Main method to start the COIL Matching System application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CoilMatchingSystemApplication.class, args);
    }
}