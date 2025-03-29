package com.meteocat.temps;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura el CORS (Cross-Origin Resource Sharing) per permetre les peticions des del frontend.
     *
     * @return WebMvcConfigurer amb la configuració de CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Afegeix les configuracions de CORS al registre.
             *
             * @param registry Registre de configuracions de CORS.
             */
            @SuppressWarnings("null") // Suppress warnings.
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Permet les peticions des de http://localhost:3000 (el port per defecte de React).
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
                // "/**" permet totes les rutes de l'API.
            }
        };
    }
}