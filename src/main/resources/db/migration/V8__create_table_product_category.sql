CREATE TABLE tb_product_category (
    product_id VARCHAR(36),
    category_id VARCHAR(36),
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id),
    FOREIGN KEY (category_id) REFERENCES tb_category(id)
);

INSERT INTO tb_product_category (product_id, category_id)
VALUES ("ceb22d57-4311-4d41-898b-8e4c690c2019", "a9fff1d3-bec3-4736-9ef3-4016273241ea");
