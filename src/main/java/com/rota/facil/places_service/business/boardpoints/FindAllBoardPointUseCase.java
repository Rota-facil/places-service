package com.rota.facil.places_service.business.boardpoints;

import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;
import com.rota.facil.places_service.persistence.mappers.BoardPointMapper;
import com.rota.facil.places_service.persistence.repositories.BoardPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllBoardPointUseCase {
    private final BoardPointRepository boardPointRepository;
    private final BoardPointMapper boardPointMapper;

    public Page<BoardPointResponseDTO> execute(Pageable pageable) {
        return boardPointRepository.findAll(pageable)
                .map(boardPointMapper::map);
    }
}
