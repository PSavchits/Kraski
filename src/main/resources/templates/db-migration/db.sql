CREATE TABLE Goods (
                       product_id SERIAL PRIMARY KEY,
                       product_name VARCHAR(100) NOT NULL,
                       description TEXT,
                       price NUMERIC(10, 2) NOT NULL,
                       available_quantity INTEGER
);

INSERT INTO goods(product_name, description, price, available_quantity) VALUES('Краска зелёная', '500мл', 7.5, 4);

ALTER TABLE Goods
    ADD COLUMN image_filename VARCHAR(255);