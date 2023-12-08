CREATE TABLE tb_user (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM("ADMIN", "SELLER", "CUSTOMER") NOT NULL
);

INSERT INTO `tb_user` (id, name, email, password, role)
VALUES
  (
    "699ad267-854f-43b3-94f1-cca894d887bc",
    "Vinicius Bortoletto",
    "ovinibortoletto@gmail.com",
    "123123",
    "ADMIN"
  ),
  (
    "9b2ae8e3-9d6a-4a54-b8b3-066f01d115ff",
    "Dalva Bortoletto",
    "davabortoletto@gmail.com",
    "123123",
    "SELLER"
  ),
  (
    "584f14d7-8b16-4a1a-b4fe-8467ec16a944",
    "Pedro Bortoletto",
    "pedrobortoletto@gmail.com",
    "123123",
    "CUSTOMER"
  )
;
