create table students
(
    id   serial primary key,
    name varchar(50)
);

create table authors
(
    id   serial primary key,
    name varchar(50)
);

create table books
(
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

-- Заполнение данными, удовлетворяющими условию
insert into students (name) values 
('Иван Иванов'),
('Петр Петров'), 
('Мария Сидорова');

insert into authors (name) values 
('Александр Пушкин'),
('Лев Толстой'),
('Федор Достоевский');

insert into books (name, author_id) values 
('Евгений Онегин', 1),
('Война и мир', 2),
('Анна Каренина', 2),
('Преступление и наказание', 3);

insert into orders (active, book_id, student_id) values 
(true, 1, 1),
(true, 2, 2),
(true, 3, 2),
(true, 4, 3);

-- Авторы, книги которых находятся на руках менее чем у 2 студентов
create view show_unpopular_authors
as
select a.name as author_name
from orders o
	join books b on o.book_id = b.id
	join authors a ON b.author_id = a.id
where o.active = true
group by a.name
having count(distinct o.student_id) < 2;

-- Получаем непопулярных авторов
select * from show_unpopular_authors