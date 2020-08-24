package com.example.table.service;

import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toDto(PersonEntity entity) {
        return Person.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .birthday(entity.getBirthday())
                .sex(entity.getSex())
                .partnerId(entity.getPartnerId())
                .build();
    }

    public PersonEntity toEntity(Person person) {
        var entity = new PersonEntity();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setPatronymic(person.getPatronymic());
        entity.setBirthday(person.getBirthday());
        entity.setSex(person.getSex());

        return entity;
    }

}
