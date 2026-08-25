package com.rota.facil.places_service.business.boardpoints;

import com.rota.facil.places_service.business.helpers.boardpoints.FindBoardPointsByIdHelper;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesBoardPointEventProducer;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import com.rota.facil.places_service.persistence.repositories.BoardPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteBoardPointUseCase {
    private final FindBoardPointsByIdHelper findBoardPointsByIdHelper;
    private final RabbitPlacesBoardPointEventProducer boardPointEventProducer;
    private final BoardPointRepository boardPointRepository;

    public void execute(UUID id, CurrentUser currentUser) {
        BoardPointEntity found = findBoardPointsByIdHelper.execute(id);

        boardPointRepository.delete(found);
        boardPointEventProducer.deleteBoardPointEvent(found, currentUser);
    }
}
