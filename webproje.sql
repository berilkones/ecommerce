CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    category_id INT REFERENCES categories(id),
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    image_url VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'Beklemede'
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id),
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL
);

INSERT INTO categories (name)
VALUES ('Telefon');

INSERT INTO categories (name)
VALUES ('Bilgisayar');

INSERT INTO categories (name)
VALUES ('Kitap');

INSERT INTO categories (name)
VALUES ('Kulaklık');

INSERT INTO products
(category_id, name, description, price, stock, image_url, is_active)
VALUES
(1,
 'iPhone 15',
 'Apple iPhone 15 128GB',
 54999.99,
 12,
 'iphone.jpg',
 true);


INSERT INTO products
(category_id, name, description, price, stock, image_url, is_active)
VALUES
(2,
 'MacBook Air M3',
 'Apple MacBook Air 13 inch',
 42999.99,
 6,
 'macbook.jpg',
 true);

