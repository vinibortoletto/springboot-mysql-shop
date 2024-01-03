CREATE TABLE tb_admin (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

INSERT INTO tb_admin (id, name, email)
VALUES ('1c1ffd91-d9f9-4327-9c94-bd3e84644cd6', 'Admin User', 'admin@example.com');
