package com.rota.facil.places_service.persistence.repositories;

import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BoardPointRepository extends JpaRepository<BoardPointEntity, UUID> {}