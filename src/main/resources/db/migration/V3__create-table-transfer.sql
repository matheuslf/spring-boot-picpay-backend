CREATE TABLE tb_transfers(
    id SERIAL PRIMARY KEY,
    payer_id INTEGER NOT NULL,
    payee_id INTEGER NOT NULL,
    value_transfer NUMERIC(19, 2) NOT NULL,
    transfer_at TIMESTAMP NOT NULL,
    FOREIGN KEY (payer_id) REFERENCES tb_users(id),
    FOREIGN KEY (payee_id) REFERENCES tb_users(id)
);