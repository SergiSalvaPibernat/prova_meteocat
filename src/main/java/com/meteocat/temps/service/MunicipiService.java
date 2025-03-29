package com.meteocat.temps.service;

import com.meteocat.temps.model.Municipi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servei que proporciona funcionalitats per obtenir informació dels municipis.
 */
@Service
public class MunicipiService {
    
    // Logger per registrar informació i errors
    private static final Logger logger = LoggerFactory.getLogger(MunicipiService.class);
    
    // URL base per obtenir dades des d'un servei S3
    @Value("${s3.base-url}")
    private String baseUrl;

    // Client per fer peticions HTTP
    private final RestTemplate restTemplate;
    
    // ObjectMapper per convertir JSON en objectes Java
    private final ObjectMapper objectMapper;

    /**
     * Constructor que inicialitza els components necessaris.
     */
    public MunicipiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Obté la llista de municipis des d'un fitxer JSON emmagatzemat a S3.
     * @return Llista d'objectes Municipi
     */
    public List<Municipi> getMunicipisFromS3(){
        String url = baseUrl + "/referencia/municipis/municipis.json";
        try{
            logger.info("Fetching municipis from URL: {}", url);
            
            // Fa una petició HTTP GET per obtenir el JSON dels municipis
            String json = restTemplate.getForObject(url, String.class);
            
            // Converteix el JSON en una llista d'objectes Municipi
            return objectMapper.readValue(json, new TypeReference<List<Municipi>>() {});
        }catch (Exception e){
            logger.error("Error llegint municipis de S3: {}", url, e);
            throw new RuntimeException("Error llegint municipis de S3: " + url, e);
        }
    }
}
