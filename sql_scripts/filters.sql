create table type
(
    id   serial primary key,
    name varchar(255)
);

create table product
(
    id           serial primary key,
    name         varchar(255),
    type_id      int references type(id),
    expired_date date,
    price        float
);

insert into type (name) values 
('СЫР'),
('МОЛОКО'),
('МЯСО'),
('МОРОЖЕНОЕ');

insert into product (name, type_id, expired_date, price) values
('Сыр Чеддер', 1, '2024-10-10', 250.00),
('Сыр Моцарелла', 1, '2023-12-15', 300.00),
('Молоко Домик в деревне', 2, '2024-10-05', 50.00),
('Мороженое пломбир', 4, '2024-11-20', 100.00),
('Мясо говядина', 3, '2023-08-30', 500.00),
('Мороженое шоколадное', 4, '2024-11-01', 120.00);

-- Все продукты с типом "СЫР"
select product.name, type.name 
from product
inner join type
on product.type_id = type.id
where type.name = 'СЫР';

-- Все продукты, содержащие в имени 'мороженое'
select name 
from product 
where name ilike '%мороженое%';

-- Просроченные продукты
select * 
from product 
where expired_date < current_date;

-- Продукты с максимальной ценой
select *
from product
where price = (select max(price) from product);

-- Количество продуктов по типу
select type.name, count(product.id)
from type 
inner join product on type.id = product.type_id
group by type.name;

-- Продукты с типом 'СЫР' и 'МОЛОКО'. В условии также можно использовать OR
select product.*
from product
inner join type on type.id = product.type_id
where type.name in('СЫР', 'МОЛОКО');

-- Типы продуктов, которых меньше 10. В моем случае, это все типы
select type.name
from type
inner join product on type.id = product.type_id
group by type.name
having count(product.id) < 10;

-- Все продукты и их типы
select product.name as Продукт, type.name as Тип
from product
inner join type on product.type_id = type.id;