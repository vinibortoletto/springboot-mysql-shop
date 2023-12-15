CREATE TABLE tb_order_product (
    product_id VARCHAR(36),
    order_id VARCHAR(36),
    PRIMARY KEY (product_id, order_id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id),
    FOREIGN KEY (order_id) REFERENCES tb_order(id)
);

INSERT INTO tb_order_product (product_id, order_id)
VALUES ("ceb22d57-4311-4d41-898b-8e4c690c2019", "48bf4f11-4600-43f2-b8f8-01add38a9f4c");
