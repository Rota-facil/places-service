package com.rota.facil.places_service.business.institutions;

import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesInstitutionEventProducer;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import com.rota.facil.places_service.persistence.mappers.InstitutionMapper;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInstitutionUseCase {
    private final InstitutionRepository institutionRepository;
    private final RabbitPlacesInstitutionEventProducer institutionEventProducer;
    private final InstitutionMapper institutionMapper;

    public InstitutionResponseDTO execute(CreateInstitutionRequestDTO request, CurrentUser currentUser) {
        InstitutionEntity preSaved = institutionMapper.map(request);
        InstitutionEntity saved = institutionRepository.save(preSaved);

        institutionEventProducer.createInstitutionEvent(saved, currentUser);

        return institutionMapper.map(saved);
    }
}
