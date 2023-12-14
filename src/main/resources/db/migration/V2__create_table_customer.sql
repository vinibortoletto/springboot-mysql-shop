CREATE TABLE tb_customer (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

INSERT INTO tb_customer (id, name, email)
VALUES
    ('ca5cc4c9-0e04-4b8c-a3be-6e9f2e050148', 'Customer 1 User', 'customer1@example.com'),
    ('5fef16b9-fc9b-4878-b09a-48efe4652933', 'Customer 2 User', 'customer2@example.com');