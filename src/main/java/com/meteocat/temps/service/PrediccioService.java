package com.meteocat.temps.service;

import com.meteocat.temps.model.Prediccio;
import com.meteocat.temps.model.PrediccioDiaria;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Servei que obté la predicció meteorològica per a un municipi.
 */
@Service
public class PrediccioService {
    
    // Logger per registrar informació i errors
    private static final Logger logger = LoggerFactory.getLogger(PrediccioService.class);
    
    // URL base on es troben les dades de predicció
    private static final String BASE_URL = "https://static-pre.meteo.cat/ginys/models/postProcessament/variables/";
    
    // Variables meteorològiques a obtenir
    private static final String[] VARIABLES = {"temperature_max", "temperature_min", "prob_prec"};
    
    // ObjectMapper per processar JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Obté la predicció meteorològica per a un municipi donat el seu codi.
     * @param codiMunicipi Codi identificador del municipi.
     * @return Objecte Prediccio amb la informació meteorològica.
     */
    public Prediccio getPrediccioPerMunicipi(String codiMunicipi) {
        List<PrediccioDiaria> prediccions = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String nomMunicipi = null;

        // Itera per obtenir la predicció per als pròxims 8 dies
        for (int dia = 1; dia <= 8; dia++) {
            Double tempMax = null;
            Double tempMin = null;
            Double probPrec = null;
            
            // Obtenció de cada variable meteorològica
            for (String var : VARIABLES) {
                String url = BASE_URL + var + "/intervals/24/" + var + "-" + dia + "_24h.json";
                try {
                    logger.info("Fetching data from URL: {}", url);
                    String resposta = restTemplate.getForObject(url, String.class);
                    if (resposta != null) {
                        JsonNode root = objectMapper.readTree(resposta);
                        JsonNode municipis = root.path("municipis");
                        
                        // Busca el municipi especificat en la resposta JSON
                        for (JsonNode municipi : municipis) {
                            if (municipi.path("codi").asText().equals(codiMunicipi)) {
                                nomMunicipi = municipi.path("nom").asText();
                                JsonNode valors = municipi.path("valors");
                                if (valors.isArray() && valors.size() > 0) {
                                    double valor = valors.get(0).path("valor").asDouble();
                                    
                                    // Assigna el valor a la variable corresponent
                                    switch (var) {
                                        case "temperature_max":
                                            tempMax = valor;
                                            break;
                                        case "temperature_min":
                                            tempMin = valor;
                                            break;
                                        case "prob_prec":
                                            probPrec = valor;
                                            break;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("Error llegint dades de predicció de S3: {}", url, e);
                    throw new RuntimeException("Error llegint dades de predicció de S3: " + url, e);
                }
            }
            
            // Afegir la predicció diària a la llista
            prediccions.add(new PrediccioDiaria(dia, tempMax, tempMin, probPrec));
        }
        
        logger.info("Retornant predicció per municipi: {} - {}", codiMunicipi, nomMunicipi);
        return new Prediccio(codiMunicipi, nomMunicipi, prediccions);
    }
}
