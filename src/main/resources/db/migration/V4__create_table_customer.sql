CREATE TABLE tb_customer (
    id VARCHAR(36) PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

INSERT INTO tb_customer (id)
VALUES ("584f14d7-8b16-4a1a-b4fe-8467ec16a944");