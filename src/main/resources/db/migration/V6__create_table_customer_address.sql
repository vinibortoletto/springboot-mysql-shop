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
    "584f14d7-8b16-4a1a-b4fe-8467ec16a944",
    "7d2c6005-0ffb-4ac5-b835-4d712fa17da7"
  );
