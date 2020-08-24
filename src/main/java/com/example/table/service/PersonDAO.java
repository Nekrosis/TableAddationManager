package com.example.table.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PersonDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> list() {
        String sql = "SELECT * FROM contacts";
        List<Person> personList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
        return personList;
    }

    public void save(Person person) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("contacts").usingColumns("firstName", "lastName", "patronymic", "birthday", "sex", "partner");
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);
        simpleJdbcInsert.execute(parameterSource);
    }

    public Person getPerson(int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        Object[] args = {id};
        Person person = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Person.class));
        return person;
    }

    public String inform(Person person) {
        return null;
    }

    public List<Person> spouse() {
        String sql = "SELECT * FROM contacts WHERE partner IS NULL";
        List<Person> personList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
        return personList;
    }

    public void delete(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void saveMarried(int id, int partnerId) {
        String sql = "UPDATE contacts SET partner=? WHERE id=?";
        jdbcTemplate.update(sql,partnerId,id);
        return;

    }
}
