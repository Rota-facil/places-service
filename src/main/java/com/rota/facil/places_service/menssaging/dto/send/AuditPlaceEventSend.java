package com.rota.facil.places_service.menssaging.dto.send;

import com.rota.facil.places_service.ResourceName;
import com.rota.facil.places_service.domain.enums.ActionType;
import com.rota.facil.places_service.domain.enums.Role;

import java.util.UUID;

public record AuditPlaceEventSend(
        UUID userId,
        Role role,
        String actionTitle,
        ActionType actionType,
        ResourceName resourceName,
        UUID resourceId
) {
}
