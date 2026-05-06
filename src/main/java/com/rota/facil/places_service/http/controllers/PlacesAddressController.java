package com.rota.facil.places_service.http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rota.facil.places_service.business.PlacesAddressService;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.places.address.CreatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.response.placesAddress.PlacesAddresResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/places-address")
@RequiredArgsConstructor
public class PlacesAddressController {
    private final PlacesAddressService placesAddressService;

    @PostMapping
    public ResponseEntity<PlacesAddresResponseDTO> createPlacesAddress(
        @AuthenticationPrincipal CurrentUser currentUser,
        @Valid @RequestBody CreatePlacesAddressRequestDTO request
    ) {
        return ResponseEntity.ok(placesAddressService.register(request, currentUser));
    }
}
