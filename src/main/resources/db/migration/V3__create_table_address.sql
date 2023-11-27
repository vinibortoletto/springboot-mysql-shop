CREATE TABLE tb_address (
    id VARCHAR(255) PRIMARY KEY,
    street VARCHAR(255),
    number VARCHAR(255),
    zip_code VARCHAR(255),
    neighborhood VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO `tb_address` (id, street, number, zip_code, neighborhood, city, state, country)
VALUES
  (
    "7d2c6005-0ffb-4ac5-b835-4d712fa17da7",
    "Rua João Sampaio",
    123,
    13403050,
    "Centro",
    "Piracicaba",
    "São Paulo",
    "Brasil"
  );
