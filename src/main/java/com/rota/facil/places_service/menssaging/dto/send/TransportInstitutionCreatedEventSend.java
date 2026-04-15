package com.rota.facil.places_service.menssaging.dto.send;

import java.util.UUID;

public record TransportInstitutionCreatedEventSend(
        UUID institutionId,
        String name,
        String latitude,
        String longitude
) {
}
