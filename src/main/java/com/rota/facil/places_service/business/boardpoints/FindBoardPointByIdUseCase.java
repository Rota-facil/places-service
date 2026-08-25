package com.rota.facil.places_service.business.boardpoints;

import com.rota.facil.places_service.business.helpers.boardpoints.FindBoardPointsByIdHelper;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;
import com.rota.facil.places_service.persistence.mappers.BoardPointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindBoardPointByIdUseCase {
    private final FindBoardPointsByIdHelper findBoardPointsByIdHelper;
    private final BoardPointMapper boardPointMapper;

    public BoardPointResponseDTO execute(UUID id) {
        return boardPointMapper.map(findBoardPointsByIdHelper.execute(id));
    }
}
