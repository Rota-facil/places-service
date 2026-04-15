package com.rota.facil.places_service.menssaging.producers;

import com.rota.facil.places_service.menssaging.dto.send.FileBoardDeleteEventSend;
import com.rota.facil.places_service.menssaging.dto.send.FileInstitutionDeleteEventSend;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitFileEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.places.exchange}")
    private String placesExchange;

    @Value("${rabbitmq.institution.deleted.routing.key}")
    private String institutionDeletedRoutingKet;

    @Value("${rabbitmq.boarding.deleted.routing.key}")
    private String boardDeletedRoutingKey;

    public void deletePrefectureEvent(FileInstitutionDeleteEventSend fileInstitutionDeleteEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, institutionDeletedRoutingKet, fileInstitutionDeleteEventSend);
    }

    public void deleteBoardEvent(FileBoardDeleteEventSend fileInstitutionDeleteEventSend) {
        rabbitTemplate.convertAndSend(placesExchange, boardDeletedRoutingKey, fileInstitutionDeleteEventSend);
    }
}

