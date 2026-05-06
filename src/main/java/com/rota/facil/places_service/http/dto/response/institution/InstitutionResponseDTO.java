package com.rota.facil.places_service.http.dto.response.institution;

import java.time.LocalDateTime;
import java.util.UUID;

public record InstitutionResponseDTO(
    UUID id,
    String name,
    Double latitude,
    Double longitude,
    LocalDateTime createdAt,
    InstitutionPlacesAddressResponseDTO placeAddress

) {
    
}