CREATE TABLE tb_cart (
    id VARCHAR(36) PRIMARY KEY,
    total DECIMAL NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(id)
);

INSERT INTO tb_cart (id, total, user_id)
VALUES ("50e54531-2ee8-4554-ae76-231cceac3311", 129.95, "5fef16b9-fc9b-4878-b09a-48efe4652933");
