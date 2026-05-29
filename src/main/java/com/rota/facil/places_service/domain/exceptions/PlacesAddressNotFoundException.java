package com.rota.facil.places_service.domain.exceptions;

public class PlacesAddressNotFoundException extends RuntimeException{
    public PlacesAddressNotFoundException(String message) { super(message); }

    public PlacesAddressNotFoundException() {
        super("O endereço não foi encontrado."); 
    }

}
