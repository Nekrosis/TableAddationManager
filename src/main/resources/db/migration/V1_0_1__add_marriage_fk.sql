alter table contacts
 add constraint marriage_fk
        foreign key (partner_id) references contacts (id);
