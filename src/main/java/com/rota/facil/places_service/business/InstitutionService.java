package com.rota.facil.places_service.business;

import com.rota.facil.places_service.domain.exceptions.InstitutionNotFoundException;
import com.rota.facil.places_service.domain.exceptions.PlacesAddressNotFoundException;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.request.institution.UpdateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesInstitutionEventProducer;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;
import com.rota.facil.places_service.persistence.mappers.InstitutionMapper;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import com.rota.facil.places_service.persistence.repositories.PlacesAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final PlacesAddressRepository placesAddressRepository;
    private final InstitutionMapper institutionMapper;
    private final RabbitPlacesInstitutionEventProducer institutionEventProducer;

    public InstitutionResponseDTO register(CreateInstitutionRequestDTO request, CurrentUser currentUser) {
        InstitutionEntity preSaved = institutionMapper.map(request);

        PlacesAddressEntity placesAddressFound = placesAddressRepository.findById(request.placesAddressId())
            .orElseThrow(PlacesAddressNotFoundException::new);

        preSaved.setPlacesAddress(placesAddressFound);

        InstitutionEntity saved = institutionRepository.save(preSaved);

        institutionEventProducer.createInstitutionEvent(saved, currentUser);

        return institutionMapper.map(saved);
    }

    public InstitutionResponseDTO findById(UUID id) {
        InstitutionEntity found = institutionRepository.findById(id)
            .orElseThrow(InstitutionNotFoundException::new);
        return institutionMapper.map(found);
    }

    public List<InstitutionResponseDTO> findAll() {
        return institutionRepository.findAll()
            .stream()
            .map(institutionMapper::map)
            .toList();
    }

    public InstitutionResponseDTO update(UUID id, UpdateInstitutionRequestDTO request, CurrentUser currentUser) {
        InstitutionEntity found = institutionRepository.findById(id)
            .orElseThrow(InstitutionNotFoundException::new);

        PlacesAddressEntity placesAddressFound = placesAddressRepository.findById(request.placesAddressId())
            .orElseThrow(PlacesAddressNotFoundException::new);

        InstitutionEntity infoToUpdate = institutionMapper.map(request);
        found.update(infoToUpdate);
        found.setPlacesAddress(placesAddressFound);

        InstitutionEntity saved = institutionRepository.save(found);
        
        institutionEventProducer.updateInstitutionEvent(saved, currentUser);
        return institutionMapper.map(saved);
    }

    public void delete(UUID id, CurrentUser currentUser) {
        InstitutionEntity found = institutionRepository.findById(id)
            .orElseThrow(InstitutionNotFoundException::new);
        institutionRepository.delete(found);
        institutionEventProducer.deleteInstitutionEvent(found, currentUser);
    }
}
