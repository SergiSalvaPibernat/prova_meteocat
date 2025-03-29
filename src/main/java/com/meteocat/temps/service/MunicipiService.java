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


@Service
public class MunicipiService {
    private static final Logger logger = LoggerFactory.getLogger(MunicipiService.class);
    @Value("${s3.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public MunicipiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<Municipi> getMunicipisFromS3(){
        String url = baseUrl + "/referencia/municipis/municipis.json";
        try{
            logger.info("Fetching municipis from URL: {}", url);
            String json = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(json, new TypeReference<List<Municipi>>() {});
        }catch (Exception e){
            logger.error("Error llegint municipis de S3: {}", url, e);
            throw new RuntimeException("Error llegint municipis de S3: " + url, e);
        }
    }

}