create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

create table habitat
(
    id             serial primary key,
    location_name  text,
    fauna_id       int references fauna(id)
);

insert into fauna (name, avg_age, discovery_date) values 
('Goldfish', 5000, '1822-01-01'),
('Tiger', 10000, '1800-02-22'),
('Parrot', 7000, '1850-10-15');

insert into habitat (location_name, fauna_id) values 
('Amazon Rainforest', 3),
('Pacific Ocean', 1),
('Savannah', 2);

select f.name as "Имя вида", h.location_name as "Среда обитания"
from fauna as f
inner join habitat as h on f.id = h.fauna_id;

select *
from fauna f
inner join habitat h on f.id = h.fauna_id
order by h.fauna_id asc;

select f.name as Животное, avg_age as Продолжительность, location_name as Локация
from fauna f
inner join habitat h on f.id = h.fauna_id
where f.avg_age > 5000;