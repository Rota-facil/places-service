package com.rota.facil.places_service.business;

import com.rota.facil.places_service.domain.exceptions.BoardPointNotFoundException;
import com.rota.facil.places_service.domain.exceptions.PlacesAddresNotFoundException;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.http.dto.request.boardpoint.CreateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.request.boardpoint.UpdateBoardPointRequestDTO;
import com.rota.facil.places_service.http.dto.response.boardpoint.BoardPointResponseDTO;
import com.rota.facil.places_service.menssaging.producers.RabbitPlacesBoardPointEventProducer;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;
import com.rota.facil.places_service.persistence.mappers.BoardPointMapper;
import com.rota.facil.places_service.persistence.repositories.BoardPointRepository;
import com.rota.facil.places_service.persistence.repositories.PlacesAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardPointService {
    private final BoardPointRepository boardPointRepository;
    private final PlacesAddressRepository placesAddressRepository;
    private final RabbitPlacesBoardPointEventProducer boardPointEventProducer;
    private final BoardPointMapper boardPointMapper;

    public BoardPointResponseDTO register(CreateBoardPointRequestDTO request, CurrentUser currentUser) {
        BoardPointEntity preSaved = boardPointMapper.map(request);

        PlacesAddressEntity placesAddressFound = placesAddressRepository.findById(request.placesAddressId())
            .orElseThrow(PlacesAddresNotFoundException::new);

        preSaved.setPlacesAddress(placesAddressFound);

        BoardPointEntity saved = boardPointRepository.save(preSaved);

        boardPointEventProducer.createBoardPointEvent(saved, currentUser);
        return boardPointMapper.map(saved);
    }

    public BoardPointResponseDTO findById(UUID id) {
        BoardPointEntity found = boardPointRepository.findById(id)
            .orElseThrow(BoardPointNotFoundException::new);
        return boardPointMapper.map(found);
    }

    public List<BoardPointResponseDTO> findAll() {
        return boardPointRepository.findAll()
            .stream()
            .map(boardPointMapper::map)
            .toList();
    }

    public BoardPointResponseDTO update(UUID id, UpdateBoardPointRequestDTO request, CurrentUser currentUser) {
        BoardPointEntity found = boardPointRepository.findById(id)
            .orElseThrow(BoardPointNotFoundException::new);

        PlacesAddressEntity placesAddressFound = placesAddressRepository.findById(request.placesAddressId())
            .orElseThrow(PlacesAddresNotFoundException::new);
            
        
        BoardPointEntity infoToUpdate = boardPointMapper.map(request);
        found.update(infoToUpdate);
        found.setPlacesAddress(placesAddressFound);

        BoardPointEntity updated = boardPointRepository.save(found);

        boardPointEventProducer.updateBoardPointEvent(updated, currentUser);
        return boardPointMapper.map(updated);
    }

    public void delete(UUID id, CurrentUser currentUser) {
        BoardPointEntity found = boardPointRepository.findById(id)
            .orElseThrow(BoardPointNotFoundException::new);

        boardPointRepository.delete(found);
        boardPointEventProducer.deleteBoardPointEvent(found, currentUser);
    }
}