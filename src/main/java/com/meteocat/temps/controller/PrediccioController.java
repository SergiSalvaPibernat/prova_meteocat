package com.meteocat.temps.controller;

import com.meteocat.temps.model.Prediccio;
import com.meteocat.temps.service.PrediccioService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/prediccio")
public class PrediccioController {

    private final PrediccioService prediccioService;

    public PrediccioController(PrediccioService prediccioService) {
        this.prediccioService = prediccioService;
    }

    @GetMapping("/{codiMunicipi}")
    public Prediccio getPrediccio(@PathVariable String codiMunicipi) {
        return this.prediccioService.getPrediccioPerMunicipi(codiMunicipi);
    }
}
