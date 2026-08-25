package com.rota.facil.places_service.business.boardpoints;

import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.boardpoint.CreateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesBoardPointEventProducer;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import com.rota.facil.places_service.persistence.mappers.BoardPointMapper;
import com.rota.facil.places_service.persistence.repositories.BoardPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBoardPointUseCase {
    private final RabbitPlacesBoardPointEventProducer boardPointEventProducer;
    private final BoardPointRepository boardPointRepository;
    private final BoardPointMapper boardPointMapper;

    public BoardPointResponseDTO execute(CreateBoardPointRequestDTO request, CurrentUser currentUser) {
        BoardPointEntity preSaved = boardPointMapper.map(request);
        BoardPointEntity saved = boardPointRepository.save(preSaved);

        boardPointEventProducer.createBoardPointEvent(saved, currentUser);
        return boardPointMapper.map(saved);
    }
}
