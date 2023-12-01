CREATE TABLE tb_category (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO tb_category (id, name)
VALUES
    (
        "a9fff1d3-bec3-4736-9ef3-4016273241ea",
        "Shoes"
    ),
    (
        "2fe1ce2d-4960-405c-8860-343b8e223906",
        "T-Shirts"
    ),
    (
        "08efd0b9-d826-45b3-acc6-2be49217cdb0",
        "Pants"
    )
;