package com.meteocat.temps.model;

public class PrediccioDiaria {
    private int dia;
    private Double tempMax;
    private Double tempMin;
    private Double probPrec;

    public PrediccioDiaria(int dia, Double tempMax, Double tempMin, Double probPrec){
        this.dia = dia;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.probPrec = probPrec;
    }

    public int getDia(){
        return this.dia;
    }

    public Double getTempMax(){
        return this.tempMax;
    }

    public Double getTempMin(){
        return this.tempMin;
    }

    public Double getProbPrec(){
        return this.probPrec;
    }
}
