package com.rota.facil.places_service.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "board_points_tb")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "board_point_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "place_address_id")
    private PlacesAddressEntity placesAddress;

    private String name;

    private String latitude;

    private String longitude;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
