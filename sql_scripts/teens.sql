CREATE TABLE teens
(
    id serial PRIMARY KEY,
    name VARCHAR(255),
    gender VARCHAR(10)
);

INSERT INTO teens (name, gender) VALUES
('Петя', 'М'),
('Маша', 'Ж'),
('Миша', 'М'),
('Соня', 'Ж'),
('Давид', 'М'),
('Даша', 'Ж'),
('Глаша', 'Ж');

-- Все возможные уникальные разнополые пары
SELECT t1.name AS Мальчик, t2.name AS Девочка
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender = 'М' AND t2.gender = 'Ж';