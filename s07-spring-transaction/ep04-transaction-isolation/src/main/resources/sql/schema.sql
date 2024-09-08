drop table if exists ACCOUNT;

create table ACCOUNT (
	id integer primary key auto_increment,
	name varchar(40) not null,
	amount integer not null
);

drop table if exists TRX_HISTORY;

create table TRX_HISTORY (
	id integer primary key auto_increment,
	description varchar(255) not null,
	trx_amount int not null,
	trx_status varchar(40) not null,
	error varchar(255),
	trx_at timestamp null default CURRENT_TIMESTAMP
);