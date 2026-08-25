package com.rota.facil.places_service.business.institutions;

import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.persistence.mappers.InstitutionMapper;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllInstitutionUseCase {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;

    public Page<InstitutionResponseDTO> execute(Pageable pageable) {
        return institutionRepository.findAll(pageable)
                .map(institutionMapper::map);
    }
}
