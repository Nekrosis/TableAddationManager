create table contacts
(
    id         int auto_increment primary key,
    first_name varchar(1000) not null,
    last_name  varchar(1000) null,
    patronymic varchar(1000) null,
    birthday   datetime      null,
    sex        varchar(1000) not null,
    partner_id int           null
);
