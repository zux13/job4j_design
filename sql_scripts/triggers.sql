-- Создание таблиц
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

-- Отключаем старые триггеры
alter table products disable trigger discount_trigger;
alter table products disable trigger tax_trigger;

-- Триггер для начисления НДС после вставки по запросу
create
or replace function add_vat_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.18
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger vat_statement_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure add_vat_statement();
	
-- Триггер для начисления НДС перед вставкой по строке
alter table products disable trigger vat_statement_trigger;

create
or replace function add_vat_row()
    returns trigger as
$$
    BEGIN
        NEW.price = NEW.price * 1.18;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';
	
create trigger vat_row_trigger
    before insert
    on products
    for each row
    execute function add_vat_row();
	
-- Триггер для логгирования истории цены
create
or replace function log_product_price_change()
    returns trigger as
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date)
		VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
		RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';
	
create trigger log_product_price_change
    after insert
    on products
    for each row
    execute function log_product_price_change();