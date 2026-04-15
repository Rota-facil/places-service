package com.rota.facil.places_service.http.dto.request;

import java.util.UUID;

public record AuthGatewayRequest(
        UUID userId,
        UUID prefectureId,
        String email,
        String role
) {
}
