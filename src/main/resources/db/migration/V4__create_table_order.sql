CREATE TABLE tb_order (
    id VARCHAR(36) PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DOUBLE NOT NULL,
    status ENUM("AWAITING PAYMENT", "PREPARING", "ON ROUTE", "DELIVERED", "CANCELED"),
    customer_id VARCHAR(36) NOT NULL,
    shipping_address_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES tb_user(id),
    FOREIGN KEY (shipping_address_id) REFERENCES tb_address(id)
);

INSERT INTO tb_order (id, total, status, customer_id, shipping_address_id)
VALUES
    (
        "48bf4f11-4600-43f2-b8f8-01add38a9f4c",
        100.0,
        "AWAITING PAYMENT",
        "699ad267-854f-43b3-94f1-cca894d887bc",
        "7d2c6005-0ffb-4ac5-b835-4d712fa17da7"
    );