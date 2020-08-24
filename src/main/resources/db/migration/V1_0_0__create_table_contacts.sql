create table contacts
(
    id         int auto_increment primary key,
    firstName  varchar(1000) not null,
    lastName   varchar(1000) null,
    patronymic varchar(1000) null,
    birthday   datetime      null,
    sex        varchar(1000) not null,
    partner    int           null
);
