package com.macelum.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macelum.api.dto.ApiInfoResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class ApiInfoController {

    @GetMapping("/info")
    @Operation(summary = "Get API metadata")
    public ApiInfoResponse getApiInfo() {
        return ApiInfoResponse.builder()
                .name("Macelum API")
                .version("v1")
                .status("ready")
                .build();
    }
}
