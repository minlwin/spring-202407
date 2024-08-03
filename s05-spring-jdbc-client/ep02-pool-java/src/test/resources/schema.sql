drop table if exists customer;

create table customer(
	id integer primary key auto_increment,
	name varchar(40) not null,
	phone varchar(12) not null,
	email varchar(40) not null
);