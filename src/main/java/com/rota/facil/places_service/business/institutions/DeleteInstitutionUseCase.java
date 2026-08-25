package com.rota.facil.places_service.business.institutions;

import com.rota.facil.places_service.business.helpers.institutions.FindInstitutionByIdHelper;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesInstitutionEventProducer;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteInstitutionUseCase {
    private final FindInstitutionByIdHelper findInstitutionByIdHelper;
    private final InstitutionRepository institutionRepository;
    private final RabbitPlacesInstitutionEventProducer institutionEventProducer;

    public void execute(UUID id, CurrentUser currentUser) {
        InstitutionEntity found = findInstitutionByIdHelper.execute(id);
        institutionRepository.delete(found);
        institutionEventProducer.deleteInstitutionEvent(found, currentUser);
    }
}
