CREATE TABLE tb_user (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM("ADMIN", "CUSTOMER") NOT NULL
);

INSERT INTO tb_user (id, name, email, password, role)
VALUES
    ('1c1ffd91-d9f9-4327-9c94-bd3e84644cd6', 'Admin User', 'admin@example.com', '$2a$10$63xUNq..HSIsC5/GFxYCD.MpJ1yGM2peVUR4YiZX.EEL3z1GOh5.u', 'ADMIN'),
    ('ca5cc4c9-0e04-4b8c-a3be-6e9f2e050148', 'Customer 1 User', 'customer1@example.com', '$2a$10$ZT56grLwe0oVqeOleWyMXO9c5uALwQgoV4CjQeNm8k7xNTkRO4ysy', 'CUSTOMER'),
    ('5fef16b9-fc9b-4878-b09a-48efe4652933', 'Customer 2 User', 'customer2@example.com', '$2a$10$uNVkRCICeeLOQNaIeemCNeZ05cFxf4sMubVn5A//OGKpHVtfWhhy2', 'CUSTOMER');