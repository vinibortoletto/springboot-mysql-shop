CREATE TABLE tb_seller (
    id VARCHAR(36) PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

INSERT INTO tb_seller (id)
VALUES ("9b2ae8e3-9d6a-4a54-b8b3-066f01d115ff");