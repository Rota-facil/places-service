package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.domain.enums.ActionType;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.dto.send.InstitutionEvent;
import com.rota.facil.places_service.menssaging.mapper.InstitutionEventMapper;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RabbitPlacesInstitutionEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final InstitutionEventMapper institutionEventMapper;

    @Value("${rabbitmq.places.exchange}")
    private String placesExchange;

    @Value("${rabbitmq.institution.created.routing.key}")
    private String institutionCreatedRoutingKey;

    @Value("${rabbitmq.institution.updated.routing.key}")
    private String institutionUpdatedRoutingKey;

    @Value("${rabbitmq.institution.deleted.routing.key}")
    private String institutionDeletedRoutingKey;

    public void createInstitutionEvent(InstitutionEntity entity, CurrentUser currentUser) {
        InstitutionEvent institutionEventSend = institutionEventMapper.map(entity, currentUser, ActionType.CREATE);
        rabbitTemplate.convertAndSend(placesExchange, institutionCreatedRoutingKey, institutionEventSend);
    }

    public void updateInstitutionEvent(InstitutionEntity entity, CurrentUser currentUser) {
        InstitutionEvent institutionEventSend = institutionEventMapper.map(entity, currentUser, ActionType.UPDATE);
        rabbitTemplate.convertAndSend(placesExchange, institutionUpdatedRoutingKey, institutionEventSend);
    }

    public void deleteInstitutionEvent(InstitutionEntity entity, CurrentUser currentUser) {
        InstitutionEvent institutionEventSend = institutionEventMapper.map(entity, currentUser, ActionType.DELETE);
        rabbitTemplate.convertAndSend(placesExchange, institutionDeletedRoutingKey, institutionEventSend);
    }
}
