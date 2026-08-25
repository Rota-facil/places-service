package com.rota.facil.places_service.http.controllers;

import com.rota.facil.places_service.business.institutions.*;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.institution.CreateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.request.institution.UpdateInstitutionRequestDTO;
import com.rota.facil.places_service.http.dto.response.institution.InstitutionResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
public class InstitutionController {
    private final CreateInstitutionUseCase createInstitutionUseCase;
    private final FindInstitutionByIdUseCase findInstitutionByIdUseCase;
    private final FindAllInstitutionUseCase findAllInstitutionUseCase;
    private final UpdateInstitutionUseCase updateInstitutionUseCase;
    private final DeleteInstitutionUseCase deleteInstitutionUseCase;

    @PostMapping
    public ResponseEntity<InstitutionResponseDTO> createInstitution(
        @Valid @RequestBody CreateInstitutionRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(createInstitutionUseCase.execute(request, currentUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(findInstitutionByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<Page<InstitutionResponseDTO>> findAll(@ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(findAllInstitutionUseCase.execute(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateInstitutionRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(updateInstitutionUseCase.execute(id, request, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable UUID id,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        deleteInstitutionUseCase.execute(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}