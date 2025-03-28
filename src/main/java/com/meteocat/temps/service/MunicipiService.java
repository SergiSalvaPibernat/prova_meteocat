package com.meteocat.temps.service;

import com.meteocat.temps.model.Municipi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;


@Service
public class MunicipiService {
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
            String json = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(json, new TypeReference<List<Municipi>>() {});
        }catch (Exception e){
            throw new RuntimeException("Error llegint municipis de S3: " + url, e);
        }
    }

}