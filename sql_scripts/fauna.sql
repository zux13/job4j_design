create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values 
('Goldfish', 5000, '1822-01-01'),
('Sharkfish', 12000, '1750-05-10'),
('Blue Whale', 35000, '1850-10-15'),
('Elephant', 25000, null),
('Salmon', 1000, '1920-07-08'),
('Eagle', 15000, '1780-11-30'),
('Tiger', 10000, '1800-02-22'),
('Parrot', 7000, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950.01.01';