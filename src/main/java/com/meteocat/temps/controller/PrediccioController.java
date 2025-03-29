package com.meteocat.temps.controller;

import com.meteocat.temps.model.Prediccio;
import com.meteocat.temps.service.PrediccioService;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST per gestionar les prediccions meteorològiques.
 */
@RestController
@RequestMapping("/api/prediccio")
public class PrediccioController {

    // Dependència del servei de prediccions
    private final PrediccioService prediccioService;

    /**
     * Constructor que injecta el servei de prediccions.
     * @param prediccioService Servei que proporciona dades de predicció meteorològica.
     */
    public PrediccioController(PrediccioService prediccioService) {
        this.prediccioService = prediccioService;
    }

    /**
     * Endpoint per obtenir la predicció meteorològica d'un municipi.
     * @param codiMunicipi Codi identificador del municipi.
     * @return Objecte Prediccio amb la informació meteorològica.
     */
    @GetMapping("/{codiMunicipi}")
    public Prediccio getPrediccio(@PathVariable String codiMunicipi) {
        return this.prediccioService.getPrediccioPerMunicipi(codiMunicipi);
    }
}

