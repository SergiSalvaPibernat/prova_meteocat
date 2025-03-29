package com.meteocat.temps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal de l'aplicació Spring Boot.
 * Gestiona l'arrencada de l'aplicació.
 */
@SpringBootApplication
public class TempsApplication {

    /**
     * Mètode principal que inicia l'aplicació Spring Boot.
     * @param args Arguments passats a l'execució de l'aplicació.
     */
    public static void main(String[] args) {
        SpringApplication.run(TempsApplication.class, args);
    }
}

