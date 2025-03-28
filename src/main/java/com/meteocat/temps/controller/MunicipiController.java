package com.meteocat.temps.controller;

import org.springframework.web.bind.annotation.*;
import com.meteocat.temps.service.MunicipiService;
import com.meteocat.temps.model.Municipi;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class MunicipiController {

    private final MunicipiService municipiService;

    public MunicipiController(MunicipiService municipiService){
        this.municipiService = municipiService;
    }

    @GetMapping("/municipis")
    public List<Municipi> getMunicipis() {
        return municipiService.getMunicipisFromS3();
    }
}
