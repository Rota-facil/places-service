package com.rota.facil.places_service.http.dto.response.placesAddress;

import java.time.LocalDateTime;
import java.util.UUID;

public record PlacesAddresResponseDTO(
    UUID id,
    String city,
    String neighborhood,
    String road,
    LocalDateTime createdAt
) {}
