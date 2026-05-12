package com.rota.facil.places_service.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rota.facil.places_service.persistence.entities.InstitutionEntity;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, UUID> {
    
}
