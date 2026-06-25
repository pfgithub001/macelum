package com.macelum.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cors")
public record CorsProperties(
        List<String> allowedOrigins,
        List<String> allowedMethods,
        List<String> allowedHeaders) {

    public CorsProperties {
        allowedOrigins = allowedOrigins == null ? new ArrayList<>() : allowedOrigins;
        allowedMethods = allowedMethods == null ? new ArrayList<>() : allowedMethods;
        allowedHeaders = allowedHeaders == null ? new ArrayList<>() : allowedHeaders;
    }
}
