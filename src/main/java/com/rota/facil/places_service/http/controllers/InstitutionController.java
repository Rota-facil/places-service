package com.rota.facil.places_service.http.controllers;

import com.rota.facil.places_service.business.InstitutionService;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;

@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
public class InstitutionController {
    private final InstitutionService institutionService;

    @PostMapping
    public ResponseEntity<InstitutionResponseDTO> createInstitution(
        @Valid @RequestBody CreateInstitutionRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(institutionService.register(request, currentUser));
    
    }

}
