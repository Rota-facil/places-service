package com.rota.facil.places_service.http.dto.response.boardpoint;

import java.util.UUID;

public record BoardPointPlacesAddressResponseDTO(
    UUID id,
    String city,
    String neighborhood
) {}
