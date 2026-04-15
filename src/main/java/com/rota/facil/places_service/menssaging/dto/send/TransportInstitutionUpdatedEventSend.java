package com.rota.facil.places_service.menssaging.dto.send;

import java.util.UUID;

public record TransportInstitutionUpdatedEventSend(
        UUID institutionId,
        String name,
        String latitude,
        String longitude
) {
}
