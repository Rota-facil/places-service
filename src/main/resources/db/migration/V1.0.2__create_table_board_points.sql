CREATE TABLE IF NOT EXISTS board_points_tb (
    board_point_id UUID PRIMARY KEY,
    place_address_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    latitude TEXT NOT NULL,
    longitude TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_board_points_places_address FOREIGN KEY (place_address_id) REFERENCES places_address_tb(place_address_id)
);