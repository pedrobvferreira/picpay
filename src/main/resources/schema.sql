DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    cpf VARCHAR(100),
    email VARCHAR(100),
    pass VARCHAR(100),
    balance DECIMAL(10, 2),
    user_type VARCHAR(100)
);

CREATE UNIQUE INDEX IF NOT EXISTS email_idx ON users (email);

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2),
    sender INT,
    receiver INT,
    created_at TIMESTAMP,
    FOREIGN KEY (sender) REFERENCES users (id),
    FOREIGN KEY (receiver) REFERENCES users (id)
);