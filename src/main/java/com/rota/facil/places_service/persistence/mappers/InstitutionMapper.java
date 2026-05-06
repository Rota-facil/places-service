package com.rota.facil.places_service.persistence.mappers;

import org.mapstruct.Mapper;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import org.mapstruct.Builder;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InstitutionMapper {
    InstitutionEntity map(CreateInstitutionRequestDTO request);
    InstitutionResponseDTO map(InstitutionEntity entity);
}
