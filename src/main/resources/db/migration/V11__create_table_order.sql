CREATE TABLE tb_order (
    id VARCHAR(36) PRIMARY KEY,
    moment TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL NOT NULL,
    status ENUM("AWAITING PAYMENT", "PREPARING", "ON ROUTE", "DELIVERED", "CANCELED") NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    shipping_address_id VARCHAR(36) NOT NULL,
    cart_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (shipping_address_id) REFERENCES tb_address(id),
    FOREIGN KEY (cart_id) REFERENCES tb_cart(id)
);

INSERT INTO tb_order (id, total, status, user_id, shipping_address_id, cart_id)
VALUES
    (
        "48bf4f11-4600-43f2-b8f8-01add38a9f4c",
        100.0,
        "AWAITING PAYMENT",
        "ca5cc4c9-0e04-4b8c-a3be-6e9f2e050148",
        "7d155b73-965c-49c0-af70-4610972303a2",
        "50e54531-2ee8-4554-ae76-231cceac3311"
    );