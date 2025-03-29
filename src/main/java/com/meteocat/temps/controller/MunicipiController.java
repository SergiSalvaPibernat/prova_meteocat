package com.meteocat.temps.controller;

import org.springframework.web.bind.annotation.*;
import com.meteocat.temps.service.MunicipiService;
import com.meteocat.temps.model.Municipi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MunicipiController {
    private static final Logger logger = LoggerFactory.getLogger(MunicipiController.class);
    private final MunicipiService municipiService;

    public MunicipiController(MunicipiService municipiService){
        this.municipiService = municipiService;
    }

    @GetMapping("/municipis")
    public List<Municipi> getMunicipis() {
        logger.info("Fetching municipis");
        List<Municipi> municipis = municipiService.getMunicipisFromS3();
        logger.info("Fetched {} municipis", municipis.size());
        return municipis;
    }
}
