package com.rota.facil.places_service.business.institutions;

import com.rota.facil.places_service.business.helpers.institutions.FindInstitutionByIdHelper;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.persistence.mappers.InstitutionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindInstitutionByIdUseCase {
    private final FindInstitutionByIdHelper findInstitutionByIdHelper;
    private final InstitutionMapper institutionMapper;

    public InstitutionResponseDTO execute(UUID id) {
        return institutionMapper.map(findInstitutionByIdHelper.execute(id));
    }
}
