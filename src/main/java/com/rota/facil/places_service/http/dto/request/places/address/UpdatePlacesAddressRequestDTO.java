package com.rota.facil.places_service.http.dto.request.places.address;

import jakarta.validation.constraints.NotBlank;

public record UpdatePlacesAddressRequestDTO(
    @NotBlank(message = "O bairro é obrigatório")
    String neighborhood,

    @NotBlank(message = "A cidade é obrigatória")
    String city,

    @NotBlank(message = "A rua é obrigatória")
    String road
) {}
