CREATE TABLE tb_customer (
    user_id VARCHAR(36) PRIMARY KEY,
    address_id VARCHAR(36),
    cart_id VARCHAR(36),
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (address_id) REFERENCES tb_address(id),
    FOREIGN KEY (cart_id) REFERENCES tb_cart(id),
);

INSERT INTO tb_customer (user_id, address_id, cart_id)
VALUES
    (
        "584f14d7-8b16-4a1a-b4fe-8467ec16a944",
        "7d2c6005-0ffb-4ac5-b835-4d712fa17da7",
        "50e54531-2ee8-4554-ae76-231cceac3311"
    );