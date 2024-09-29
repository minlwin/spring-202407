drop table if exists EMPLOYEE;

create table EMPLOYEE (
	code varchar(8) not null,
	name varchar(20) not null,
	phone varchar(12) not null,
	role varchar(10) not null,
	entry_date date not null,
	primary key (code)
);