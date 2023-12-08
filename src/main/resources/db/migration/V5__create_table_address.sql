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

INSERT INTO `tb_address` (id, street, number, zipcode, neighborhood, city, state, country)
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
