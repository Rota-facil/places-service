package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.ResourceName;
import com.rota.facil.places_service.domain.enums.PlaceAuditAction;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.dto.send.InstitutionCreatedEvent;
import com.rota.facil.places_service.menssaging.dto.send.InstitutionDeletedEvent;
import com.rota.facil.places_service.menssaging.dto.send.InstitutionUpdatedEvent;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitPlacesInstitutionEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.places.exchange}")
    private String placesExchange;

    @Value("${rabbitmq.institution.created.routing.key}")
    private String institutionCreatedRoutingKey;

    @Value("${rabbitmq.institution.updated.routing.key}")
    private String institutionUpdatedRoutingKey;

    @Value("${rabbitmq.institution.deleted.routing.key}")
    private String institutionDeletedRoutingKey;

    public void createInstitutionEvent(InstitutionEntity entity, CurrentUser currentUser) {
        PlaceAuditAction auditAction = PlaceAuditAction.INSTITUTION_CREATED;
        InstitutionCreatedEvent eventSend = new InstitutionCreatedEvent(
                currentUser.userId(),
                currentUser.prefectureId(),
                currentUser.role(),
                currentUser.email(),
                auditAction.title(currentUser.email(), entity.getName()),
                auditAction.getActionType(),
                ResourceName.INSTITUTION.name(),
                entity.getId(),
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude()
        );

        rabbitTemplate.convertAndSend(placesExchange, institutionCreatedRoutingKey, eventSend);
    }

    public void updateInstitutionEvent(InstitutionEntity entity, CurrentUser currentUser) {
        PlaceAuditAction auditAction = PlaceAuditAction.INSTITUTION_UPDATED;
        InstitutionUpdatedEvent eventSend = new InstitutionUpdatedEvent(
                currentUser.userId(),
                currentUser.prefectureId(),
                currentUser.role(),
                currentUser.email(),
                auditAction.title(currentUser.email(), entity.getName()),
                auditAction.getActionType(),
                ResourceName.INSTITUTION.name(),
                entity.getId(),
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude()
        );

        rabbitTemplate.convertAndSend(placesExchange, institutionUpdatedRoutingKey, eventSend);
    }

    public void deleteInstitutionEvent(InstitutionEntity entity, CurrentUser currentUser) {
        PlaceAuditAction auditAction = PlaceAuditAction.INSTITUTION_DELETED;
        InstitutionDeletedEvent eventSend = new InstitutionDeletedEvent(
                currentUser.userId(),
                currentUser.prefectureId(),
                currentUser.role(),
                currentUser.email(),
                auditAction.title(currentUser.email(), entity.getName()),
                auditAction.getActionType(),
                ResourceName.INSTITUTION.name(),
                entity.getId(),
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude()
        );

        rabbitTemplate.convertAndSend(placesExchange, institutionDeletedRoutingKey, eventSend);
    }
}
