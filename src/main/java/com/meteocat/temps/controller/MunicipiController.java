package com.meteocat.temps.controller;

import org.springframework.web.bind.annotation.*;
import com.meteocat.temps.service.MunicipiService;
import com.meteocat.temps.model.Municipi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Controlador REST per gestionar les operacions relacionades amb els municipis.
 */
@RestController
@RequestMapping("/api")
public class MunicipiController {
    
    // Logger per registrar informació sobre les peticions i respostes
    private static final Logger logger = LoggerFactory.getLogger(MunicipiController.class);
    
    // Dependència del servei que maneja la lògica dels municipis
    private final MunicipiService municipiService;

    /**
     * Constructor que injecta el servei dels municipis
     * @param municipiService Servei que proporciona dades dels municipis
     */
    public MunicipiController(MunicipiService municipiService){
        this.municipiService = municipiService;
    }

    /**
     * Endpoint per obtenir la llista de municipis disponibles.
     * @return Llista d'objectes Municipi
     */
    @GetMapping("/municipis")
    public List<Municipi> getMunicipis() {
        logger.info("Fetching municipis");
        
        // Obté els municipis des del servei (per exemple, des d'un emmagatzematge S3)
        List<Municipi> municipis = municipiService.getMunicipisFromS3();
        
        logger.info("Fetched {} municipis", municipis.size());
        return municipis;
    }
}