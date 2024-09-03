CREATE TABLE car_bodies
(
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_engines
(
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_transmissions
(
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE cars
(
    id serial PRIMARY KEY,
    name VARCHAR(255),
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES
('Sedan'),
('Hatchback'),
('SUV'),
('Coupe');

INSERT INTO car_engines (name) VALUES
('Petrol'),
('Diesel'),
('Electric'),
('Hybrid');

INSERT INTO car_transmissions (name) VALUES
('Manual'),
('Automatic'),
('CVT'),
('Semi-automatic');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Corolla', 1, 1, 2),
('Nissan Leaf', 2, 3, 2),
('Tesla Model S', 1, 3, 2),
('Ford Mustang', 4, 1, NULL),
('Custom Car', NULL, NULL, NULL);

-- Авто со всеми деталями
select c.id, c.name name, b.name body, e.name engine, t.name transmission
from cars c
	left join car_bodies b on c.body_id = b.id
	left join car_engines e on c.engine_id = e.id
	left join car_transmissions t on c.transmission_id = t.id;
	
-- Кузова, не использующиеся ни в одной машине
select b.name from car_bodies b
	left join cars c on b.id = c.body_id
where c.body_id is null;

-- Двигатели, не использующиеся ни в одной машине
select e.name from car_engines e
	left join cars c on e.id = c.engine_id
where c.engine_id is null;

-- Трансмиссии, не использующиеся ни в одной машине
select t.name from car_transmissions t
	left join cars c on t.id = c.transmission_id
where c.transmission_id is null;