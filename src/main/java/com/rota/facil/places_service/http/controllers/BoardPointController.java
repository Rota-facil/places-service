package com.rota.facil.places_service.http.controllers;

import com.rota.facil.places_service.business.BoardPointService;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.boardpoint.CreateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.request.boardpoint.UpdateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/board-points")
@RequiredArgsConstructor
public class BoardPointController {
    private final BoardPointService boardPointService;

    @PostMapping
    public ResponseEntity<BoardPointResponseDTO> create(
        @Valid @RequestBody CreateBoardPointRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(boardPointService.register(request, currentUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardPointResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(boardPointService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BoardPointResponseDTO>> findAll() {
        return ResponseEntity.ok(boardPointService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardPointResponseDTO> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateBoardPointRequestDTO request,
        @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return ResponseEntity.ok(boardPointService.update(id, request, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @AuthenticationPrincipal CurrentUser currentUser) {
        boardPointService.delete(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}