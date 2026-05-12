package com.rota.facil.places_service.domain.exceptions;

public class BoardPointNotFoundException extends RuntimeException {
    public BoardPointNotFoundException() {
        super("Ponto de embarque não encontrado");
    }
}