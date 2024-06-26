DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    cpf VARCHAR(100),
    email VARCHAR(500),
    pass TEXT,
    balance DECIMAL(10, 2),
    type TEXT NOT NULL,
    role TEXT NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS email_idx ON users (email);
CREATE UNIQUE INDEX IF NOT EXISTS cpf_idx ON users (cpf);

CREATE TABLE IF NOT EXISTS transactions (
    id int AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10, 2),
    sender INT,
    receiver INT,
    created_at TIMESTAMP,
    FOREIGN KEY (sender) REFERENCES users (id),
    FOREIGN KEY (receiver) REFERENCES users (id)
);