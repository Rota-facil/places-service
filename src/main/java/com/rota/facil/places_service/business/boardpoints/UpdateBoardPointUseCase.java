package com.rota.facil.places_service.business.boardpoints;

import com.rota.facil.places_service.business.helpers.boardpoints.FindBoardPointsByIdHelper;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.boardpoint.UpdateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesBoardPointEventProducer;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import com.rota.facil.places_service.persistence.mappers.BoardPointMapper;
import com.rota.facil.places_service.persistence.repositories.BoardPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateBoardPointUseCase {
    private final FindBoardPointsByIdHelper findBoardPointsByIdHelper;
    private final BoardPointRepository boardPointRepository;
    private final BoardPointMapper boardPointMapper;
    private final RabbitPlacesBoardPointEventProducer boardPointEventProducer;

    public BoardPointResponseDTO execute(UUID id, UpdateBoardPointRequestDTO request, CurrentUser currentUser) {
        BoardPointEntity found = findBoardPointsByIdHelper.execute(id);

        BoardPointEntity infoToUpdate = boardPointMapper.map(request);
        found.update(infoToUpdate);
        BoardPointEntity updated = boardPointRepository.save(found);

        boardPointEventProducer.updateBoardPointEvent(updated, currentUser);
        return boardPointMapper.map(updated);
    }
}
