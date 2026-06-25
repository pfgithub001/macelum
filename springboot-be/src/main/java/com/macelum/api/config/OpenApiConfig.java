package com.macelum.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiDocumentation() {
        return new OpenAPI().info(new Info()
                .title("Macelum API")
                .version("v1")
                .description("Spring Boot backend aligned with SPRING_BOOT_GUIDE.md."));
    }
}
