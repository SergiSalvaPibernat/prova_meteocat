package com.meteocat.temps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que representa un municipi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Municipi {

    // Codi identificador del municipi
    private String codi;
    
    // Nom del municipi
    private String nom;

    /**
     * Constructor buit necessari per a la deserialització JSON.
     */
    public Municipi() {
    }

    /**
     * Obté el codi del municipi.
     * @return Codi del municipi
     */
    public String getCodi() {
        return this.codi;
    }

    /**
     * Estableix el codi del municipi.
     * @param codi Nou codi del municipi
     */
    public void setCodi(String codi) {
        this.codi = codi;
    }

    /**
     * Obté el nom del municipi.
     * @return Nom del municipi
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Estableix el nom del municipi.
     * @param nom Nou nom del municipi
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}

