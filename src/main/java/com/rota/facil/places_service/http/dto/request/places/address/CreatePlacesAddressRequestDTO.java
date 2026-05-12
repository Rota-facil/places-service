package com.rota.facil.places_service.http.dto.request.places.address;

import jakarta.validation.constraints.NotBlank;

public record CreatePlacesAddressRequestDTO(
    @NotBlank(message = "O bairro é obrigatório")
    String neighborhood,
    
    @NotBlank(message = "A cidade é obrigatório")
    String city,
    
    @NotBlank(message = "A rua é obrigatório")
    String road
) {
}