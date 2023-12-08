CREATE TABLE tb_cart (
    id VARCHAR(36) PRIMARY KEY,
    total DECIMAL NOT NULL,
    customer_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);

INSERT INTO tb_cart (id, total, customer_id)
VALUES ("50e54531-2ee8-4554-ae76-231cceac3311", 129.95, "584f14d7-8b16-4a1a-b4fe-8467ec16a944");
