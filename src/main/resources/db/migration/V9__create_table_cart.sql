CREATE TABLE tb_cart (
    user_id VARCHAR(36),
    product_id VARCHAR(36),
    quantity INT,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id)
);

INSERT INTO tb_cart (user_id, product_id, quantity)
VALUES
    ("699ad267-854f-43b3-94f1-cca894d887bc", "ceb22d57-4311-4d41-898b-8e4c690c2019", 3),
    ("699ad267-854f-43b3-94f1-cca894d887bc", "3c9d07d1-f5bc-4596-aca1-4ca1e006580a", 5);
