CREATE TABLE tb_admin (
    id VARCHAR(36) PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

INSERT INTO tb_admin (id)
VALUES ("699ad267-854f-43b3-94f1-cca894d887bc");