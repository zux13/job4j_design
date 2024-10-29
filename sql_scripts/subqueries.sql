CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
    ('John', 'Doe', 28, 'USA'),
    ('Jane', 'Smith', 34, 'UK'),
    ('Alice', 'Johnson', 23, 'Canada'),
    ('Bob', 'Brown', 45, 'Australia'),
	('Roman', 'Afanasyev', 23, 'Russia'),
    ('Anisa', 'Cole', 25, 'Italy'),
    ('Charlie', 'Davis', 29, 'USA');

INSERT INTO orders (amount, customer_id)
VALUES
    (100, 1), 
    (200, 2), 
    (250, 2),
    (300, 3), 
    (350, 3),
    (400, 3),
    (450, 4), 
    (500, 4),
    (550, 7),
    (600, 7);
	
SELECT first_name, last_name, age
FROM customers
WHERE age = (
    SELECT MIN(age)
    FROM customers
);
	
SELECT first_name, last_name
FROM customers
WHERE id NOT IN (
    SELECT customer_id
    FROM orders
);
