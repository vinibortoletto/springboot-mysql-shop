CREATE TABLE tb_address (
    id VARCHAR(36) PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(20) NOT NULL,
    zipcode VARCHAR(10) NOT NULL,
    neighborhood VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

INSERT INTO tb_address (id, street, number, zipcode, neighborhood, city, state, country)
VALUES
    ('48b93991-f44d-4d2e-b724-2d2cf6d75869', 'Main Street', '123', '12345', 'Downtown', 'Cityville', 'Stateville', 'Countryland'),
    ('7d155b73-965c-49c0-af70-4610972303a2', 'Oak Avenue', '456', '67890', 'Suburbia', 'Townsville', 'Provinceville', 'Countryland'),
    ('98df2656-b7bd-4eee-8e70-5bc618b1780b', 'Pine Road', '789', '54321', 'Green Hills', 'Villagetown', 'Regionville', 'Countryland');

