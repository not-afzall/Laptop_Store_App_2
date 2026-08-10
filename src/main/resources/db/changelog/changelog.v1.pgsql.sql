--liquibase formatted sql

--changeset afzal:1
CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(50)
);

CREATE TABLE laptops(
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    processor VARCHAR(50),
    ram INT,
    storage INT,
    price DOUBLE PRECISION,
    condition VARCHAR(50),
    image_url VARCHAR(255),
    sold BOOLEAN DEFAULT FALSE,
    seller_id INT,
    CONSTRAINT fk_seller_id FOREIGN KEY (seller_id) REFERENCES users(id)
);

CREATE TABLE orders(
    id SERIAL PRIMARY KEY,
    order_date TIMESTAMP,
    status VARCHAR(50),
    buyer_id INT,
    laptop_id INT,
    CONSTRAINT fk_buyer_id FOREIGN KEY (buyer_id) REFERENCES users(id),
    CONSTRAINT fk_laptop_id FOREIGN KEY (laptop_id) REFERENCES laptops(id)
);
