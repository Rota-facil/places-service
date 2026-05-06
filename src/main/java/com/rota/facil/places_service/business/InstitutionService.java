package com.rota.facil.places_service.business;

import org.springframework.stereotype.Service;

import com.rota.facil.places_service.domain.exceptions.PlacesAddresNotFoundException;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesInstitutionEventProducer;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;
import com.rota.facil.places_service.persistence.mappers.InstitutionMapper;
import com.rota.facil.places_service.persistence.repositories.InstitutionRepository;
import com.rota.facil.places_service.persistence.repositories.PlacesAddressRepository;
import lombok.RequiredArgsConstructor;


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
            .orElseThrow(PlacesAddresNotFoundException::new);  

        preSaved.setPlacesAddress(placesAddressFound);

        InstitutionEntity saved = institutionRepository.save(preSaved);

        institutionEventProducer.createInstitutionEvent(saved, currentUser);

        return institutionMapper.map(saved); 
    }


}
