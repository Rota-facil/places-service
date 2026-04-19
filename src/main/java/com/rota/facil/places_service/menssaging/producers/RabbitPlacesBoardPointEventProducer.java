package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.ResourceName;
import com.rota.facil.places_service.domain.enums.ActionType;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.dto.send.BoardPointEvent;
import com.rota.facil.places_service.menssaging.mapper.BoardPointEventMapper;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RabbitPlacesBoardPointEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final BoardPointEventMapper boardPointEventMapper;

    @Value("${rabbitmq.places.exchange}")
    private String placesExchange;

    @Value("${rabbitmq.boarding.created.routing.key}")
    private String boardPointCreatedRoutingKey;

    @Value("${rabbitmq.boarding.updated.routing.key}")
    private String boardPointUpdatedRoutingKey;

    @Value("${rabbitmq.boarding.deleted.routing.key}")
    private String boardPointDeletedRoutingKey;

    public void createBoardPointEvent(BoardPointEntity boardPointEntity, CurrentUser currentUser) {
        BoardPointEvent boardPointEventSend = boardPointEventMapper.map(boardPointEntity, currentUser, ActionType.CREATE);
        rabbitTemplate.convertAndSend(placesExchange, boardPointCreatedRoutingKey, boardPointEventSend);
    }

    public void updateBoardPointEvent(BoardPointEntity boardPointEntity, CurrentUser currentUser) {
        BoardPointEvent boardPointEventSend = boardPointEventMapper.map(boardPointEntity, currentUser, ActionType.UPDATE);
        rabbitTemplate.convertAndSend(placesExchange, boardPointUpdatedRoutingKey, boardPointEventSend);
    }

    public void deleteBoardPointEvent(BoardPointEntity boardPointEntity, CurrentUser currentUser) {
        BoardPointEvent boardPointEventSend = boardPointEventMapper.map(boardPointEntity, currentUser, ActionType.DELETE);
        rabbitTemplate.convertAndSend(placesExchange, boardPointDeletedRoutingKey, boardPointEventSend);
    }
}
