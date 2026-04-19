package com.rota.facil.places_service.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "places_address_tb")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlacesAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "place_address_id")
    private UUID id;

    private String city;

    private String neighborhood;

    private String road;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
