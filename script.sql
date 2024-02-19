

CREATE TABLE cliente_model (
    id UUID PRIMARY KEY,
    limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL
);


DO $$
BEGIN
    INSERT INTO cliente_model (id, limite, saldo)
    VALUES 
    ('a1f95147-22c5-4f68-b90a-fc0d77f7687f', 100000, 0),
    ('c8d1d807-3f14-4180-8f00-aa86f3aa5048', 80000, 0),
    ('f232ea60-e90b-4d66-9317-d2c93f22fc9e', 1000000, 0),
    ('2d8a7ae8-5e01-4248-89c1-f4ec392ffe47', 10000000, 0),
    ('edf4f375-c5ea-4129-93ca-6c164d1d34ef', 500000, 0);
END; $$