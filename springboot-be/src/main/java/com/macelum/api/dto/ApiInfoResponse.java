package com.macelum.api.dto;

import lombok.Builder;

@Builder
public record ApiInfoResponse(
        String name,
        String version,
        String status) {
}
