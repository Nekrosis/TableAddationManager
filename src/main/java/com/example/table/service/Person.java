package com.example.table.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
@Data
@NoArgsConstructor

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
