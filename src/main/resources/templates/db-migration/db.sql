CREATE TABLE Goods (
                       product_id SERIAL PRIMARY KEY,
                       product_name VARCHAR(100) NOT NULL,
                       description TEXT,
                       price NUMERIC(10, 2) NOT NULL,
                       available_quantity INTEGER,
                       image_filename VARCHAR(255),
                       date_added DATE,
                       date_updated DATE,
                       category VARCHAR(100)
);

INSERT INTO goods(product_name, description, price, available_quantity) VALUES('Краска зелёная', '500мл', 7.5, 4);
