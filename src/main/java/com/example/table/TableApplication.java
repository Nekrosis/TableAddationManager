package com.example.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableApplication.class, args);
    }


}
