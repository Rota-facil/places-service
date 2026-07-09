package com.rota.facil.places_service.menssaging.dto.send;

import java.util.UUID;

public record InstitutionCreatedEvent(
        UUID userId,
        UUID prefectureId,
        String role,
        String userEmail,
        String actionTitle,
        String actionType,
        String resourceName,
        UUID resourceId,
        UUID institutionId,
        String name,
        Double latitude,
        Double longitude
) {
}
