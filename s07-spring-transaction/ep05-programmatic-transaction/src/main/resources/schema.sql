set foreign_key_checks = 0;

drop table if exists MEMBERS;
create table MEMBERS (
	id int primary key auto_increment,
	name varchar(40) not null,
	login_id varchar(40) not null,
	role varchar(10) not null,
	password varchar(255) not null,
	regist_at timestamp null default CURRENT_TIMESTAMP
);

drop table if exists CATEGORY;
create table CATEGORY (
	id int primary key auto_increment,
	name varchar(40) not null
);


drop table if exists PRODUCT;

create table PRODUCT (
	id int primary key auto_increment,
	name varchar(40) not null,
	unit_price int not null,
	category_id int not null,
	description varchar(255),
	foreign key (category_id) references CATEGORY(id)
);

drop table if exists SALE_HISTORY;

create table SALE_HISTORY (
	id int primary key auto_increment,
	members_id int not null,
	status varchar(20) null default 'Initiated',
	remark varchar(255),
	create_at timestamp null default CURRENT_TIMESTAMP,
	update_at timestamp null default CURRENT_TIMESTAMP,
	foreign key (members_id) references MEMBERS(id)
);

drop table if exists SALE_ITEM;

create table SALE_ITEM (
	sale_id int not null,
	product_id int not null,
	unit_price int not null,
	quantity int not null,
	primary key (sale_id, product_id),
	foreign key (sale_id) references SALE(id),
	foreign key (product_id) references PRODUCT(id)
);

set foreign_key_checks = 1;


