CREATE TABLE IF NOT EXISTS user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE REFERENCES user(id),
    balance DECIMAL(10, 2) DEFAULT 0.00
);

CREATE TABLE IF NOT EXISTS email_data (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE REFERENCES user(id), -- Уникальность для одного email
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS phone_data (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE REFERENCES user(id), -- Уникальность для одного номера телефона
    phone VARCHAR(20) UNIQUE NOT NULL
);
