package com.rota.facil.places_service.http.controllers;

import com.rota.facil.places_service.business.PlacesAddressService;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.places.address.CreatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.request.places.address.UpdatePlacesAddressRequestDTO;
import com.rota.facil.places_service.http.dto.response.placesAddress.PlacesAddresResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<PlacesAddresResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(placesAddressService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlacesAddresResponseDTO>> findAll() {
        return ResponseEntity.ok(placesAddressService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlacesAddresResponseDTO> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdatePlacesAddressRequestDTO request
    ) {
        return ResponseEntity.ok(placesAddressService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        placesAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}