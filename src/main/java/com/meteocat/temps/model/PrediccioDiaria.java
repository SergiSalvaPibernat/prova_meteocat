package com.meteocat.temps.model;

/**
 * Classe que representa la predicció meteorològica per un dia concret.
 */
public class PrediccioDiaria {
    
    // Dia de la predicció (format numèric, per exemple 1 per dilluns)
    private int dia;
    
    // Temperatura màxima prevista
    private Double tempMax;
    
    // Temperatura mínima prevista
    private Double tempMin;
    
    // Probabilitat de precipitació
    private Double probPrec;

    /**
     * Constructor per inicialitzar una predicció diària.
     * @param dia Dia de la predicció
     * @param tempMax Temperatura màxima
     * @param tempMin Temperatura mínima
     * @param probPrec Probabilitat de precipitació
     */
    public PrediccioDiaria(int dia, Double tempMax, Double tempMin, Double probPrec){
        this.dia = dia;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.probPrec = probPrec;
    }

    /**
     * Obté el dia de la predicció.
     * @return Dia de la setmana (format numèric)
     */
    public int getDia(){
        return this.dia;
    }

    /**
     * Obté la temperatura màxima prevista.
     * @return Temperatura màxima
     */
    public Double getTempMax(){
        return this.tempMax;
    }

    /**
     * Obté la temperatura mínima prevista.
     * @return Temperatura mínima
     */
    public Double getTempMin(){
        return this.tempMin;
    }

    /**
     * Obté la probabilitat de precipitació.
     * @return Probabilitat de precipitació
     */
    public Double getProbPrec(){
        return this.probPrec;
    }
}
