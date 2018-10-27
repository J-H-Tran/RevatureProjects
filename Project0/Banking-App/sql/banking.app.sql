drop table balance_table;
drop table bank_user;
drop sequence pk_user_id_seq;
drop sequence pk_account_id_seq;
drop sequence account_num_seq;

--ALTER TABLE bank_test.balance_table DROP CONSTRAINT SYS_C004048;
--drop user bank_test cascade;
--CREATE USER bank_test IDENTIFIED BY bt123;
--GRANT DBA TO bank_test WITH ADMIN OPTION;
CREATE TABLE BANK_USER
(
  USER_ID NUMBER NOT NULL,
  AUTHORIZE VARCHAR2(20),
  FIRST_NAME VARCHAR2(20) NOT NULL,
  LAST_NAME VARCHAR2(20) NOT NULL,
  USERNAME VARCHAR2(20) NOT NULL,
  PASSWORD VARCHAR2(20) NOT NULL,
  account_no number not null,
  approval_status varchar2(3),
  CONSTRAINT PK_USER_ID PRIMARY KEY (USER_ID),
  CONSTRAINT UNQ_USERNAME UNIQUE (USERNAME)
);

create table balance_table 
(
    account_id number not null,
    user_id number not null ,
    type_id number not null,
    balance_amt number not null,
    constraint pk_account_id primary key (account_id)
);

alter table balance_table add foreign KEY (USER_ID) references bank_user (user_id);

select * from bank_user;
select * from balance_table;

Create sequence pk_user_id_seq
	minvalue 0
	maxvalue 1000
	start with 1
	increment by 1;
Create sequence pk_account_id_seq
	minvalue 0
	maxvalue 1000
	start with 1
	increment by 1;
Create sequence account_num_seq
	minvalue 6999
	maxvalue 8000
	start with 7000
	increment by 1;

create or replace procedure add_user(authz varchar2, firstname varchar2, lastname varchar2,
username varchar2, pword varchar2, approval varchar2, accountType number, balance number)
as
tempVal number(10);
begin
    tempVal := pk_user_id_seq.nextval;

    insert into bank_user values(
    tempVal, authz, firstname, lastname, username, pword, 
    account_num_seq.nextval, approval);
    
    insert into balance_table values(
    pk_account_id_seq.nextval, tempVal, accountType, balance);
    commit;
end;
/

--insert into bank_user values(0, 'admin', 'jonathan', 'tran', 'jt1', 'jt1', 6999, 'yes');

select * from bank_user;
select * from balance_table;

commit;