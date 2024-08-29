CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(100)
);

CREATE TABLE rules (
	id SERIAL PRIMARY KEY,
	rule_name VARCHAR(100)
);

CREATE TABLE roles_rules (
	role_id INT REFERENCES roles(id),
	rule_id INT REFERENCES rules(id),
	PRIMARY KEY (role_id, rule_id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(100),
	role_id INT REFERENCES roles(id)
);

CREATE TABLE categories (
	id SERIAL PRIMARY KEY,
	category_name VARCHAR(100)
);

CREATE TABLE states (
	id SERIAL PRIMARY KEY,
	state_name VARCHAR(100)
);

CREATE TABLE items (
	id SERIAL PRIMARY KEY,
	item_name VARCHAR(100),
	user_id INT REFERENCES users(id),
	category_id INT REFERENCES categories(id),
	state_id INT REFERENCES states(id)
);

CREATE TABLE comments (
	id SERIAL PRIMARY KEY,
	comment TEXT,
	item_id INT REFERENCES items(id)
);

CREATE TABLE attachs (
	id SERIAL PRIMARY KEY,
	attachment VARCHAR(255),
	item_id INT REFERENCES items(id)
);