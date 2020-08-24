alter table contacts
 add constraint marriage_fk
        foreign key (partner) references contacts (id);
