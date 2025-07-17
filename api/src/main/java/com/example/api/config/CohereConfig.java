package com.example.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CohereConfig {

    @Value("${cohere.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
