insert into TRX_BASE (account_id, trx_type, status, ledger, before_usb, amount) values ('aung@gmail.com', 'CASH_IN', 'Success', 'Credit', 0, 200000);
insert into TRX_BASE (account_id, trx_type, status, ledger, before_usb, amount) values ('nilar@gmail.com', 'CASH_IN', 'Success', 'Credit', 0, 800000);

insert into TRX_CASH_IN(id, cash_in_from) values (1, 'MPU');
insert into TRX_CASH_IN(id, cash_in_from) values (2, 'MPU');

insert into BALANCE_HISTORY(trx_id, account_id, before_amount, trx_amount, ledger) values ('1', 'aung@gmail.com', 0, 200000, 'Credit');
insert into BALANCE_HISTORY(trx_id, account_id, before_amount, trx_amount, ledger) values ('2', 'nilar@gmail.com', 0, 800000, 'Credit');

update ACCOUNT set amount = 200000 where login_id = 'aung@gmail.com';
update ACCOUNT set amount = 800000 where login_id = 'nilar@gmail.com';

