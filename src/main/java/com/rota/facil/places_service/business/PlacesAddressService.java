package com.rota.facil.places_service.business;

import com.rota.facil.places_service.domain.exceptions.PlacesAddressNotFoundException;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.places.address.CreatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.request.places.address.UpdatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.response.placesAddress.PlacesAddresResponseDTO;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;
import com.rota.facil.places_service.persistence.mappers.PlacesAddressMapper;
import com.rota.facil.places_service.persistence.repositories.PlacesAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlacesAddressService {
    private final PlacesAddressRepository placesAddressRepository;
    private final PlacesAddressMapper placesAddressMapper;

    public PlacesAddresResponseDTO register(CreatePlacesAddressRequestDTO request, CurrentUser currentUser) {
        PlacesAddressEntity preSaved = placesAddressMapper.map(request);
        return placesAddressMapper.map(placesAddressRepository.save(preSaved));
    }

    public PlacesAddresResponseDTO findById(UUID id) {
        PlacesAddressEntity found = placesAddressRepository.findById(id)
            .orElseThrow(PlacesAddressNotFoundException::new);
        return placesAddressMapper.map(found);
    }

    public List<PlacesAddresResponseDTO> findAll() {
        return placesAddressRepository.findAll()
            .stream()
            .map(placesAddressMapper::map)
            .toList();
    }

    public PlacesAddresResponseDTO update(UUID id, UpdatePlacesAddressRequestDTO request) {
        PlacesAddressEntity found = placesAddressRepository.findById(id)
            .orElseThrow(PlacesAddressNotFoundException::new);
        
        PlacesAddressEntity infoToUpdate = placesAddressMapper.map(request);
        found.update(infoToUpdate);
        return placesAddressMapper.map(placesAddressRepository.save(found));
    }

    public void delete(UUID id) {
        PlacesAddressEntity found = placesAddressRepository.findById(id)
            .orElseThrow(PlacesAddressNotFoundException::new);
        placesAddressRepository.delete(found);
    }
}