CREATE TABLE IF NOT EXISTS places_address_tb (
    place_address_id UUID PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    road VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);