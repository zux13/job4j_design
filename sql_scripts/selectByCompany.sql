-- Создание таблиц
CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

-- Заполнение таблиц
INSERT INTO company (id, name) VALUES
(1, 'Google'),
(2, 'Microsoft'),
(3, 'Apple'),
(4, 'Amazon'),
(5, 'Facebook');

INSERT INTO person (id, name, company_id) VALUES
(1, 'Alice', 1),
(2, 'Bob', 2),
(3, 'Charlie', 1),
(4, 'Diana', 3),
(5, 'Eve', 4),
(6, 'Frank', 5),
(7, 'Grace', 2),
(8, 'Hank', 3),
(9, 'Ivy', 4),
(10, 'Jack', 4),
(11, 'Pauline', 3);

-- Все сотрудники, не работающие в компании 5
SELECT 
    p.name AS person_name, 
    c.name AS company_name
FROM 
    person p LEFT JOIN company c 
ON 
    p.company_id = c.id
WHERE 
    p.company_id != 5;
	
-- Компании с максимальным количеством сотрудников
WITH CompanyCounts AS (
    SELECT 
        c.name AS company_name,
        COUNT(p.id) AS person_count
    FROM 
        company c
    LEFT JOIN 
        person p
    ON 
        c.id = p.company_id
    GROUP BY 
        c.name
),
MaxCounts AS (
    SELECT 
        MAX(person_count) AS max_count
    FROM 
        CompanyCounts
)
SELECT 
    cc.company_name, 
    cc.person_count
FROM 
    CompanyCounts cc
JOIN 
    MaxCounts mc
ON 
    cc.person_count = mc.max_count;
