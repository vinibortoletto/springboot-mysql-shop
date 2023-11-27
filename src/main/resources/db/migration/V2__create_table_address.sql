CREATE TABLE tb_address (
    id VARCHAR(36) PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    zip_code INT NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
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
