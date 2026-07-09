package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.ResourceName;
import com.rota.facil.places_service.domain.enums.PlaceAuditAction;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.dto.send.BoardPointCreatedEvent;
import com.rota.facil.places_service.menssaging.dto.send.BoardPointDeletedEvent;
import com.rota.facil.places_service.menssaging.dto.send.BoardPointUpdatedEvent;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitPlacesBoardPointEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.places.exchange}")
    private String placesExchange;

    @Value("${rabbitmq.boarding.created.routing.key}")
    private String boardPointCreatedRoutingKey;

    @Value("${rabbitmq.boarding.updated.routing.key}")
    private String boardPointUpdatedRoutingKey;

    @Value("${rabbitmq.boarding.deleted.routing.key}")
    private String boardPointDeletedRoutingKey;

    public void createBoardPointEvent(BoardPointEntity entity, CurrentUser currentUser) {
        PlaceAuditAction auditAction = PlaceAuditAction.BOARD_POINT_CREATED;
        BoardPointCreatedEvent eventSend = new BoardPointCreatedEvent(
                currentUser.userId(),
                currentUser.prefectureId(),
                currentUser.role(),
                currentUser.email(),
                auditAction.title(currentUser.email(), entity.getName()),
                auditAction.getActionType(),
                ResourceName.BOARD_POINT.name(),
                entity.getId(),
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude()
        );

        rabbitTemplate.convertAndSend(placesExchange, boardPointCreatedRoutingKey, eventSend);
    }

    public void updateBoardPointEvent(BoardPointEntity entity, CurrentUser currentUser) {
        PlaceAuditAction auditAction = PlaceAuditAction.BOARD_POINT_UPDATED;
        BoardPointUpdatedEvent eventSend = new BoardPointUpdatedEvent(
                currentUser.userId(),
                currentUser.prefectureId(),
                currentUser.role(),
                currentUser.email(),
                auditAction.title(currentUser.email(), entity.getName()),
                auditAction.getActionType(),
                ResourceName.BOARD_POINT.name(),
                entity.getId(),
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude()
        );

        rabbitTemplate.convertAndSend(placesExchange, boardPointUpdatedRoutingKey, eventSend);
    }

    public void deleteBoardPointEvent(BoardPointEntity entity, CurrentUser currentUser) {
        PlaceAuditAction auditAction = PlaceAuditAction.BOARD_POINT_DELETED;
        BoardPointDeletedEvent eventSend = new BoardPointDeletedEvent(
                currentUser.userId(),
                currentUser.prefectureId(),
                currentUser.role(),
                currentUser.email(),
                auditAction.title(currentUser.email(), entity.getName()),
                auditAction.getActionType(),
                ResourceName.BOARD_POINT.name(),
                entity.getId(),
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude()
        );

        rabbitTemplate.convertAndSend(placesExchange, boardPointDeletedRoutingKey, eventSend);
    }
}
