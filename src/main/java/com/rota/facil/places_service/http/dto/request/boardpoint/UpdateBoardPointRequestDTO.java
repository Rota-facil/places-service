package com.rota.facil.places_service.http.dto.request.boardpoint;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBoardPointRequestDTO(
    @NotNull(message = "O endereço é obrigatório")
    UUID placesAddressId,

    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotNull(message = "A latitude é obrigatória")
    Double latitude,

    @NotNull(message = "A longitude é obrigatória")
    Double longitude
) {}
