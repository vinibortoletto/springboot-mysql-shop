CREATE TABLE tb_cart_product (
    cart_id VARCHAR(36),
    product_id VARCHAR(36),
    quantity INT,
    subtotal DOUBLE,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES tb_cart(id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id)
);

INSERT INTO tb_cart_product (cart_id, product_id, quantity, subtotal)
VALUES
    ("50e54531-2ee8-4554-ae76-231cceac3311", "ceb22d57-4311-4d41-898b-8e4c690c2019", 2, 39.98),
    ("50e54531-2ee8-4554-ae76-231cceac3311", "3c9d07d1-f5bc-4596-aca1-4ca1e006580a", 3, 89.97);
