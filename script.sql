

CREATE TABLE cliente_model (
    id SERIAL PRIMARY KEY,
    limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL
);


DO $$
BEGIN
    INSERT INTO cliente_model (limite, saldo)
    VALUES 
    (100000, 0),
    (80000, 0),
    (1000000, 0),
    (10000000, 0),
    (500000, 0);
END; $$