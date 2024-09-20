create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

begin transaction;

insert into products (name, producer, count, price) 
values('product_4', 'producer_1', 4, 44);

-- Сохраняем сразу два сейвпоинта
savepoint first_savepoint;
savepoint second_savepoint;

-- Обновляем количество по условию и перезаписываем первый сейвпоинт
update products
set count = count + 10
where producer = 'producer_1';

savepoint first_savepoint;

-- Обновляем цены и сохраняем в третий сейвпоинт
update products
set price = price + 100
where producer != 'producer_1';

savepoint third_savepoint;

-- Случайно удаляем все записи
delete from products;

-- Делаем rollback к первому сейвпоинту (на таймлайне средний)
rollback to first_savepoint;

-- Возвращаемся к самой ранней точке
rollback to second_savepoint;

-- Попытка возврата к более поздней точке приводит к ошибке
rollback to third_savepoint;

-- Попытка коммита приведет к rollback
ROLLBACK;