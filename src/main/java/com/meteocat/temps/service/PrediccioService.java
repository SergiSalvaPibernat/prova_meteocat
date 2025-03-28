package com.meteocat.temps.service;

import com.meteocat.temps.model.Prediccio;
import com.meteocat.temps.model.PrediccioDiaria;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrediccioService {
    private static final String BASE_URL = "https://static-pre.meteo.cat/ginys/models/postProcessament/variables/";
    private static final String[] VARIABLES = {"temperature_max", "temperature_min", "prob_prec"};

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Prediccio getPrediccioPerMunicipi(String codiMunicipi){
        List<PrediccioDiaria> prediccions = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (int dia = 1; dia <=8; dia++){
            Double tempMax = null;
            Double tempMin = null;
            Double probPrec = null;

            for (String var : VARIABLES){
                String url = BASE_URL + var + "/intervals/24/" + var + "-" + dia + "_24h.json";
                String resposta = restTemplate.getForObject(url, String.class);

                if(resposta != null){
                    try{
                        JsonNode root = objectMapper.readTree(resposta);
                        JsonNode municipis = root.path("municipis");

                        for(JsonNode municipi : municipis){
                            if(municipi.path("codi").asText().equals(codiMunicipi)){
                                JsonNode valors = municipi.path("valors");
                                if(valors.isArray() && valors.size() > 0){
                                    double valor = valors.get(0).path("valor").asDouble();
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

                    }catch (Exception e){
                        throw new RuntimeException("Error llegint dades de predicció de S3: " + url, e);
                    }
                }
            }
            prediccions.add(new PrediccioDiaria(dia, tempMax, tempMin, probPrec));
        }
        return new Prediccio(codiMunicipi, prediccions);
    }
}
