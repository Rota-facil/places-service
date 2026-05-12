package com.rota.facil.places_service.persistence.mappers;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.rota.facil.places_service.http.dto.request.places.address.CreatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.request.places.address.UpdatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.response.placesAddress.PlacesAddresResponseDTO;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PlacesAddressMapper {
    PlacesAddressEntity map(CreatePlacesAddressRequestDTO request);
    PlacesAddressEntity map(UpdatePlacesAddressRequestDTO request);
    PlacesAddresResponseDTO map(PlacesAddressEntity entity);
}