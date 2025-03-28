package com.meteocat.temps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediccio {
    private String codiMunicipi;
    private List<PrediccioDiaria> prediccions;

    public Prediccio(String codiMunicipi, List<PrediccioDiaria> prediccions){
        this.codiMunicipi = codiMunicipi;
        this.prediccions = prediccions;
    }

    public String getCodiMunicipi(){
        return this.codiMunicipi;
    }

    public List<PrediccioDiaria> getPrediccions(){
        return this.prediccions;
    }
} 