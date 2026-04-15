package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.menssaging.dto.send.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitTransportEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.places.exchange}")
    private String placesExchange;

    @Value("${rabbitmq.institution.created.routing.key}")
    private String institutionCreatedRoutingKet;

    @Value("${rabbitmq.institution.updated.routing.key}")
    private String institutionUpdatedRoutingKet;

    @Value("${rabbitmq.institution.deleted.routing.key}")
    private String institutionDeletedRoutingKet;


    @Value("${rabbitmq.boarding.created.routing.key}")
    private String boardCreatedRoutingKey;

    @Value("${rabbitmq.boarding.updated.routing.key}")
    private String boardUpdatedRoutingKey;

    @Value("${rabbitmq.boarding.deleted.routing.key}")
    private String boardDeletedRoutingKey;

    public void createInstitutionEvent(TransportInstitutionCreatedEventSend transportInstitutionCreatedEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionCreatedRoutingKet, transportInstitutionCreatedEventSend);
    }

    public void updateInstitutionEvent(TransportInstitutionUpdatedEventSend transportInstitutionUpdatedEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionUpdatedRoutingKet, transportInstitutionUpdatedEventSend);
    }

    public void deleteInstitutionEvent(TransportInstitutionDeletedEventSend transportInstitutionDeletedEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionDeletedRoutingKet, transportInstitutionDeletedEventSend);
    }

    public void createBoardEvent(TransportBoardPointCreatedEventSend transportBoardPointCreatedEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardCreatedRoutingKey, transportBoardPointCreatedEventSend);
    }

    public void updateBoardEvent(TransportBoardPointUpdatedEventSend transportBoardPointUpdatedEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardUpdatedRoutingKey, transportBoardPointUpdatedEventSend);
    }

    public void deleteBoardEvent(TransportBoardPointDeletedEventSend transportBoardPointDeletedEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardDeletedRoutingKey, transportBoardPointDeletedEventSend);
    }
}
