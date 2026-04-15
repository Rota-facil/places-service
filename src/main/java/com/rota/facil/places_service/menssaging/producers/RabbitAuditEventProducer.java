package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.menssaging.dto.send.AuditPlaceEventSend;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitAuditEventProducer {
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

    public void createInstitutionEvent(AuditPlaceEventSend auditPlaceEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionCreatedRoutingKet, auditPlaceEventSend);
    }

    public void updateInstitutionEvent(AuditPlaceEventSend auditPlaceEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionUpdatedRoutingKet, auditPlaceEventSend);
    }

    public void deleteInstitutionEvent(AuditPlaceEventSend auditPlaceEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionDeletedRoutingKet, auditPlaceEventSend);
    }

    public void createBoardEvent(AuditPlaceEventSend auditPlaceEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardCreatedRoutingKey, auditPlaceEventSend);
    }

    public void updateBoardEvent(AuditPlaceEventSend auditPlaceEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardUpdatedRoutingKey, auditPlaceEventSend);
    }

    public void deleteBoardEvent(AuditPlaceEventSend auditPlaceEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardDeletedRoutingKey, auditPlaceEventSend);
    }

}

