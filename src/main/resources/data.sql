/* Clear transactions */
DELETE FROM transactions;

DELETE FROM users;

/* Insert users */
INSERT INTO
    users (
        id, name, cpf, email, pass, balance, user_type
    )
VALUES (
        1, 'Joao - User', '12345678900', 'joao@test.com', '123456', 1000.00, 'COMMON'
    );

INSERT INTO
    users (
        id, name, cpf, email, pass, balance, user_type
    )
VALUES (
        2, 'Maria - Lojista', '12345678901', 'maria@test.com', '123456', 1000.00, 'MERCHANT'
    );