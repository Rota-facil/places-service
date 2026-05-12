package com.rota.facil.places_service.persistence.mappers;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.rota.facil.places_service.http.dto.request.boardpoint.CreateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.request.boardpoint.UpdateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BoardPointMapper {
    BoardPointEntity map(CreateBoardPointRequestDTO request);
    BoardPointEntity map(UpdateBoardPointRequestDTO request);
    BoardPointResponseDTO map(BoardPointEntity entity);
}
