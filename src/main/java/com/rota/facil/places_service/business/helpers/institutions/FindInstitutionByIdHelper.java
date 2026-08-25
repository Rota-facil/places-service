package com.rota.facil.places_service.business.helpers.institutions;

import com.rota.facil.places_service.domain.exceptions.InstitutionNotFoundException;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindInstitutionByIdHelper {
    private final InstitutionRepository institutionRepository;

    public InstitutionEntity execute(UUID id) {
        return institutionRepository.findById(id)
                .orElseThrow(InstitutionNotFoundException::new);
    }
}
