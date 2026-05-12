package com.rota.facil.places_service.persistence.mappers;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.request.institution.UpdateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InstitutionMapper {
    InstitutionEntity map(CreateInstitutionRequestDTO request);
    InstitutionEntity map(UpdateInstitutionRequestDTO request);
    InstitutionResponseDTO map(InstitutionEntity entity);
}