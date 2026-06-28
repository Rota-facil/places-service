package com.rota.facil.places_service.http.dto.request.institution;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateInstitutionRequestDTO(
    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotNull(message = "A latitude é obrigatório")
    Double latitude,

    @NotNull(message = "A longitude é obrigatória")
    Double longitude
) {
}
