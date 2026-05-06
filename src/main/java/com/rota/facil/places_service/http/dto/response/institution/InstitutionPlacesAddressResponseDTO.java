package com.rota.facil.places_service.http.dto.response.institution;

import java.util.UUID;

public record InstitutionPlacesAddressResponseDTO(
    UUID id,
    String city,
    String neighborhood
) {
    
}
