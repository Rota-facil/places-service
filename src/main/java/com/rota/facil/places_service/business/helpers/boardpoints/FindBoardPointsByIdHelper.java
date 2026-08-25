package com.rota.facil.places_service.business.helpers.boardpoints;

import com.rota.facil.places_service.domain.exceptions.BoardPointNotFoundException;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import com.rota.facil.places_service.persistence.repositories.BoardPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBoardPointsByIdHelper {
    private final BoardPointRepository boardPointRepository;

    public BoardPointEntity execute(UUID id) {
        return boardPointRepository.findById(id)
                .orElseThrow(BoardPointNotFoundException::new);
    }
}
