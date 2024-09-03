CREATE TABLE departments 
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE employees
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	department_id INT REFERENCES departments (id)
);

INSERT INTO departments (name) VALUES
('HR'),
('IT'),
('Finance'),
('Marketing'),
('PR')

INSERT INTO employees (name, department_id) VALUES
('Alice', 1),
('Bob', 2),
('Charlie', 2),
('David', 3),
('Eve', 4);

-- Левое соединение
select * from employees
	left join departments 
	on employees.department_id = departments.id;

-- Правое соединение
select * from departments
	right join employees 
	on departments.id = employees.department_id;

-- Полное соединение
select * from employees
	full join departments 
	on employees.department_id = departments.id;

-- Кросс соединение
select * from employees
	cross join departments;

-- Департаменты без работников
select d.id, d.name from departments d
	left join employees e
	on d.id = e.department_id
where e.department_id is null;

-- Одинаковый результат для левого и правого соединения
select e.id, e.name, d.id, d.name from employees e
	left join departments d
	on e.department_id = d.id
order by e.id;

select e.id, e.name, d.id, d.name from departments d
	right join employees e
	on d.id = e.department_id
order by e.id;