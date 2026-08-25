package com.rota.facil.places_service.business.institutions;

import com.rota.facil.places_service.business.helpers.institutions.FindInstitutionByIdHelper;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.institution.UpdateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesInstitutionEventProducer;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import com.rota.facil.places_service.persistence.mappers.InstitutionMapper;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateInstitutionUseCase {
    private final FindInstitutionByIdHelper findInstitutionByIdHelper;
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;
    private final RabbitPlacesInstitutionEventProducer institutionEventProducer;

    public InstitutionResponseDTO execute(UUID id, UpdateInstitutionRequestDTO request, CurrentUser currentUser) {
        InstitutionEntity found = findInstitutionByIdHelper.execute(id);

        InstitutionEntity infoToUpdate = institutionMapper.map(request);
        found.update(infoToUpdate);

        InstitutionEntity saved = institutionRepository.save(found);

        institutionEventProducer.updateInstitutionEvent(saved, currentUser);
        return institutionMapper.map(saved);
    }
}
