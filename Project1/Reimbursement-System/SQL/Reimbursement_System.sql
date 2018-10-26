alter table reimb_table drop constraint fk_e_id;
alter table reimb_table drop constraint fk_m_appr_mngr;
drop table empl_table;
drop table reimb_table;
drop table mngr_table;

create table empl_table (
    e_id number,
    e_job_id number(4),
    e_position varchar2(20),
    e_firstname varchar2(20),
    e_lastname varchar2(20),
    e_email varchar2(20), 
    e_pword varchar2(20),
    constraint pk_e_id primary key (e_id),
    constraint unq_e_email unique (e_email)
);

create table mngr_table (
    m_appr_mngr varchar(20),
    m_id number,
    m_position varchar2(20),
    m_firstname varchar2(20),
    m_lastname varchar2(20),
    m_email varchar2(20),
    m_pword varchar2(20),
    constraint pk_m_appr_mngr primary key (m_appr_mngr),
    constraint unq_m_email unique (m_email)
);

create table reimb_table (
    r_id number,
    e_id number,
    r_type varchar2(20),
    r_cost number,
    r_status varchar2(20),
    r_appr_mngr varchar2(20),
    constraint pk_r_id primary key (r_id)
);

alter table reimb_table add constraint fk_e_id foreign key
    (e_id) references empl_table (e_id);
    
alter table reimb_table add constraint fk_m_appr_mngr foreign key
    (r_appr_mngr) references mngr_table (m_appr_mngr);

--select * from empl_table;

commit;