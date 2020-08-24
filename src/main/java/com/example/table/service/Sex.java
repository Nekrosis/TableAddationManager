package com.example.table.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex {
    MAN("Мужчина"),
    WOMEN("Женщина");

    private final String sex;
}
