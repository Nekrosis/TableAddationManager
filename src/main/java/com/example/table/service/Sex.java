package com.example.table.service;

public enum Sex {
    MAN("Мужчина"), WOMEN("Женщина");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
