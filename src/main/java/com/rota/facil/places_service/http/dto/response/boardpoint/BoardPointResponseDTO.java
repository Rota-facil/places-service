package com.rota.facil.places_service.http.dto.response.boardpoint;

import java.time.LocalDateTime;
import java.util.UUID;

public record BoardPointResponseDTO(
    UUID id,
    String name,
    Double latitude,
    Double longitude,
    LocalDateTime createdAt,
    BoardPointPlacesAddressResponseDTO placesAddress
) {}