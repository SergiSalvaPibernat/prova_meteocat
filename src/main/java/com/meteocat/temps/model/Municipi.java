package com.meteocat.temps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Municipi {

    private String codi;
    private String nom;

    public Municipi() {
    }

    public String getCodi() {
        return this.codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}

/* La classe té nom i codi (els camps importants del JSON).

L'annotació @JsonIgnoreProperties(ignoreUnknown = true) ignora camps innecessaris.

Inclou getters i setters per Spring Boot */
