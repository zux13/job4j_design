create table fleet(
	id serial primary key,
	name varchar(255),
	created date,
	number_of_guns integer,
	in_service boolean
);
insert into fleet(name, created, number_of_guns, in_service) values('Bismark', '1939-02-14', 72, true);
update fleet set in_service = false;
delete from fleet;