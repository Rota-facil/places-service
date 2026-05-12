package com.rota.facil.places_service.domain.exceptions;

public class InstitutionNotFoundException extends RuntimeException {
    public InstitutionNotFoundException() {
        super("Instituição não encontrada");
    }
}