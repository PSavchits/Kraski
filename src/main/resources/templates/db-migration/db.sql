CREATE TABLE Goods (
                       product_id SERIAL PRIMARY KEY,
                       product_name VARCHAR(100) NOT NULL,
                       description TEXT,
                       price NUMERIC(10, 2) NOT NULL,
                       available_quantity INTEGER,
                       image_filename VARCHAR(255),
                       date_added DATE,
                       date_updated DATE,
                       category_id INTEGER REFERENCES Categories(category_id)
);

CREATE TABLE Categories (
                            category_id SERIAL PRIMARY KEY,
                            category_name VARCHAR(100) NOT NULL,
                            parent_category_id INTEGER REFERENCES Categories(category_id)
);
