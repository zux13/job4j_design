create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

INSERT INTO products (name, producer, count, price)
VALUES 
	('product_1', 'producer_1', 10, 100),
	('product_2', 'producer_1', 5, 199),
	('product_3', 'producer_3', 8, 14),
	('product_4', 'producer_2', 0, 829),
	('product_5', 'producer_2', 7, 77),
	('product_6', 'producer_4', 24, 145);
	
-- Процедура удаления данных из таблицы
CREATE OR REPLACE PROCEDURE delete_products(u_id integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        IF u_id > 0 THEN
            DELETE FROM products
            WHERE id = u_id;
		ELSE
			TRUNCATE TABLE products;
			ALTER SEQUENCE products_id_seq RESTART WITH 1;
        END IF;
    END;
$$;

-- Функция удаления данных из таблицы
CREATE OR REPLACE FUNCTION f_delete_products(u_id integer)
RETURNS INTEGER
LANGUAGE 'plpgsql'
AS $$
	DECLARE
		result INTEGER;
	BEGIN
		IF u_id > 0 THEN
			SELECT INTO result COUNT(id) 
			FROM products
			WHERE id = u_id;
			DELETE FROM products
			WHERE id = u_id;
		ELSE
			SELECT INTO result COUNT(*) FROM products;
			TRUNCATE TABLE products;
			ALTER SEQUENCE products_id_seq RESTART WITH 1;
		END IF;
		RETURN result;
	END;
$$;

-- Примеры вызовов
CALL delete_products(1);
SELECT f_delete_products(0);