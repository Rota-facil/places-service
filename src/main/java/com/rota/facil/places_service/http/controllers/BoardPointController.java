package com.rota.facil.places_service.http.controllers;

import com.rota.facil.places_service.business.boardpoints.*;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.boardpoint.CreateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.request.boardpoint.UpdateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;

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
@RequestMapping("/board-points")
@RequiredArgsConstructor
public class BoardPointController {
    private final CreateBoardPointUseCase createBoardPointUseCase;
    private final FindBoardPointByIdUseCase findBoardPointByIdUseCase;
    private final FindAllBoardPointUseCase findAllBoardPointUseCase;
    private final UpdateBoardPointUseCase updateBoardPointUseCase;
    private final DeleteBoardPointUseCase deleteBoardPointUseCase;

    @PostMapping
    public ResponseEntity<BoardPointResponseDTO> create(
        @Valid @RequestBody CreateBoardPointRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(createBoardPointUseCase.execute(request, currentUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardPointResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(findBoardPointByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<Page<BoardPointResponseDTO>> findAll(@ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(findAllBoardPointUseCase.execute(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardPointResponseDTO> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateBoardPointRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(updateBoardPointUseCase.execute(id, request, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @AuthenticationPrincipal CurrentUser currentUser) {
        deleteBoardPointUseCase.execute(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}