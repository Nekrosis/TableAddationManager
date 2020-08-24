package com.example.table.service;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contacts")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @JsonEnumDefaultValue
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "partner_id", insertable = false, updatable = false)
    private Integer partnerId;

}
