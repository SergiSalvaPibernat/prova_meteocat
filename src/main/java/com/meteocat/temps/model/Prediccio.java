package com.meteocat.temps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Classe que representa una predicció meteorològica per un municipi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediccio {
    
    // Codi identificador del municipi
    private String codiMunicipi;
    
    // Nom del municipi
    private String nomMunicipi;
    
    // Llista de prediccions diàries per al municipi
    private List<PrediccioDiaria> prediccions;

    /**
     * Constructor per inicialitzar una instància de Prediccio.
     * @param codiMunicipi Codi del municipi
     * @param nomMunicipi Nom del municipi
     * @param prediccions Llista de prediccions diàries
     */
    public Prediccio(String codiMunicipi, String nomMunicipi, List<PrediccioDiaria> prediccions){
        this.codiMunicipi = codiMunicipi;
        this.nomMunicipi = nomMunicipi;
        this.prediccions = prediccions;
    }

    /**
     * Obté el codi del municipi.
     * @return Codi del municipi
     */
    public String getCodiMunicipi(){
        return this.codiMunicipi;
    }

    /**
     * Obté el nom del municipi.
     * @return Nom del municipi
     */
    public String getNomMunicipi(){
        return this.nomMunicipi;
    }

    /**
     * Obté la llista de prediccions diàries.
     * @return Llista de prediccions diàries
     */
    public List<PrediccioDiaria> getPrediccions(){
        return this.prediccions;
    }
}
