package com.rota.facil.places_service.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rota.facil.places_service.persistence.entities.PlacesAddressEntity;

@Repository
public interface PlacesAddressRepository extends JpaRepository<PlacesAddressEntity, UUID> {
    
}
