package com.rota.facil.places_service.business;

import org.springframework.stereotype.Service;

import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.places.address.CreatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.response.placesAddress.PlacesAddresResponseDTO;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;
import com.rota.facil.places_service.persistence.mappers.PlacesAddressMapper;
import com.rota.facil.places_service.persistence.repositories.PlacesAddressRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PlacesAddressService {
    private final PlacesAddressRepository placesAddressRepository;
    private final PlacesAddressMapper placesAddressMapper;
    
    public PlacesAddresResponseDTO register(CreatePlacesAddressRequestDTO request, CurrentUser currentUser) {
        PlacesAddressEntity preSaved = placesAddressMapper.map(request);
        return placesAddressMapper.map(placesAddressRepository.save(preSaved));
    }
}
