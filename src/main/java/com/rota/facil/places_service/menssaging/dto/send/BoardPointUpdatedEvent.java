package com.rota.facil.places_service.menssaging.dto.send;

import java.util.UUID;

public record BoardPointUpdatedEvent(
        UUID userId,
        String role,
        String userEmail,
        String actionTitle,
        String actionType,
        String resourceName,
        UUID resourceId,
        UUID boardId,
        String name,
        Double latitude,
        Double longitude
) {
}
