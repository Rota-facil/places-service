package com.rota.facil.places_service.http.controllers;

import com.rota.facil.places_service.business.InstitutionService;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.request.institution.UpdateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(institutionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<InstitutionResponseDTO>> findAll() {
        return ResponseEntity.ok(institutionService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateInstitutionRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(institutionService.update(id, request, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable UUID id,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        institutionService.delete(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}