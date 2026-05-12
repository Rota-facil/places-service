package com.rota.facil.places_service.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "institutions_tb")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "institution_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "place_address_id")
    private PlacesAddressEntity placesAddress;

    private String name;

    private Double latitude;

    private Double longitude;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public void update(InstitutionEntity infoToUpdate) {
        if (infoToUpdate.getName() != null) this.name = infoToUpdate.getName();
        if (infoToUpdate.getLatitude() != null) this.latitude = infoToUpdate.getLatitude();
        if (infoToUpdate.getLongitude() != null) this.longitude = infoToUpdate.getLongitude();
    }
}
