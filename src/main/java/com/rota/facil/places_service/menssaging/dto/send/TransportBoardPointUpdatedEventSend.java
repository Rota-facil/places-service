package com.rota.facil.places_service.menssaging.dto.send;

import java.util.UUID;

public record TransportBoardPointUpdatedEventSend(
        UUID boardId,
        String name,
        String latitude,
        String longitude
) {
}
