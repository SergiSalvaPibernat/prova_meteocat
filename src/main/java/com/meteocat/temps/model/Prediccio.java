package com.meteocat.temps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediccio {
    private String codiMunicipi;
    private String nomMunicipi;
    private List<PrediccioDiaria> prediccions;

    public Prediccio(String codiMunicipi, String nomMunicipi, List<PrediccioDiaria> prediccions){
        this.codiMunicipi = codiMunicipi;
        this.nomMunicipi = nomMunicipi;
        this.prediccions = prediccions;
    }

    public String getCodiMunicipi(){
        return this.codiMunicipi;
    }

    public String getNomMunicipi(){
        return this.nomMunicipi;
    }

    public List<PrediccioDiaria> getPrediccions(){
        return this.prediccions;
    }
} 