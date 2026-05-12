package com.rota.facil.places_service.domain.exceptions;

public class PlacesAddresNotFoundException extends RuntimeException{
    public PlacesAddresNotFoundException(String message) { super(message); }

    public PlacesAddresNotFoundException() { 
        super("O endereço não foi encontrado."); 
    }

}
