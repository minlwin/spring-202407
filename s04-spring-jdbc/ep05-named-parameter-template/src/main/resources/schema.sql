create table category (
	id int generated by default as identity,
	name varchar(40) not null unique,
	primary key (id)
);

create table product (
	id int generated by default as identity,
	name varchar(40) not null,
	category_id int not null,
	price int not null,
	amount int not null,
	unit varchar(10) not null,
	primary key(id),
	constraint fk_product_to_category foreign key (category_id) references category
);