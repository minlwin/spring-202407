set foreign_key_check = 0;

drop table if exist ACCOUNT;

create table ACCOUNT (
	login_id varchar(20) primary key,
	name varchar(40) not null,
	level varchar(10) not null,
	amount integer default 0
);

drop table if exist TRX_BASE;

create table TRX_BASE(
	id integer primary key auto_increment,
	trx_type varchar(10) not null,
	ledger boolean not null,
	account_id varchar(20) not null,
	before_usb int not null,
	amount int not null,
	particular varchar(255),
	constraint trx_cash_in_to_account foreign key (account_id) references ACCOUNT (login_id)
);

drop table if exist TRX_CASH_IN;

create table TRX_CASH_IN (
	id integer primary key,
	cash_in_from varchar(40) not null,
	constraint trx_cash_in_to_base foreign key (id) references TRX_BASE,
);


drop table if exist TRX_TRANSFER;

create table TRX_TRANSFER (
	id integer primary key,
	transfer_to varchar(20) not null,
	before_tsfto int not null,
	constraint trx_transfer_to_base foreign key (id) references TRX_BASE,
	constraint trx_transfer_to_account foreign key (transfer_to) references ACCOUNT (login_id)
);

drop table if exist BALANCE_HISTORY;

create table BALANCE_HISTORY (
	trx_id int not null,
	account_id varchar(20) not null,
	seq_number int not null,
	before_amount int not null,
	trx_amount int not null,
	ledger boolean not null,
	particular varchar(255),
	constraint balance_history_to_trx foreign key (trx_id) references TRX_BASE (id),
	constraint balance_history_to_account foreign key (account_id) references ACCOUNT (login_id)
);

drop table if exist LIMIT_SETTING;

create table LIMIT_SETTING (
	id integer primary key auto_increment,
	user_level varchar(40) not null,
	trx_type varchar(10) not null,
	min_limit integer not null,
	max_limit integer not null,
	daily_limit integer not null
);

set foreign_key_check = 1;