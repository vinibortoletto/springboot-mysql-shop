CREATE TABLE tb_user_address (
    user_id VARCHAR(36),
    address_id VARCHAR(36),
    PRIMARY KEY (user_id, address_id),
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (address_id) REFERENCES tb_address(id)
);

INSERT INTO tb_user_address (user_id, address_id)
VALUES
    ("699ad267-854f-43b3-94f1-cca894d887bc", "7d2c6005-0ffb-4ac5-b835-4d712fa17da7"),
    ("9b2ae8e3-9d6a-4a54-b8b3-066f01d115ff", "7d2c6005-0ffb-4ac5-b835-4d712fa17da7"),
    ("584f14d7-8b16-4a1a-b4fe-8467ec16a944", "7d2c6005-0ffb-4ac5-b835-4d712fa17da7");