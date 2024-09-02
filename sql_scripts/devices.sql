create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices (name, price) values 
('iPhone', 999.99),
('Samsung Galaxy', 899.99),
('Dell Laptop', 1200.50),
('Sony Headphones', 150.75),
('Apple Watch', 299.00);

insert into people (name) values 
('John Doe'),
('Jane Smith'),
('Alice Johnson'),
('Bob Brown'),
('Charlie Davis');


insert into devices_people (device_id, people_id) values 
(1, 1), 
(2, 2), 
(3, 1), 
(4, 3), 
(5, 4), 
(1, 5);

select avg(price) from devices;

select people.name, avg(devices.price)
from devices
inner join devices_people on devices.id = devices_people.device_id
inner join people on devices_people.people_id = people.id
group by people.name;

-- Небольшое отступление от условия задачи
select people.name, avg(devices.price)
from devices
inner join devices_people on devices.id = devices_people.device_id
inner join people on devices_people.people_id = people.id
group by people.name
having avg(devices.price) > 300;