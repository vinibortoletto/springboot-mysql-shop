CREATE TABLE tb_customer_address (
    customer_id VARCHAR(36),
    address_id VARCHAR(36),
    PRIMARY KEY (customer_id, address_id),
    FOREIGN KEY (customer_id) REFERENCES tb_customer(id),
    FOREIGN KEY (address_id) REFERENCES tb_address(id)
);

INSERT INTO `tb_customer_address` (customer_id, address_id)
VALUES
  (
    "ca5cc4c9-0e04-4b8c-a3be-6e9f2e050148",
    "48b93991-f44d-4d2e-b724-2d2cf6d75869"
  ),
  (
    "ca5cc4c9-0e04-4b8c-a3be-6e9f2e050148",
    "7d155b73-965c-49c0-af70-4610972303a2"
  ),
  (
    "5fef16b9-fc9b-4878-b09a-48efe4652933",
    "98df2656-b7bd-4eee-8e70-5bc618b1780b"
  )
;
