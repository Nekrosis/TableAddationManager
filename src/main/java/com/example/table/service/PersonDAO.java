package com.example.table.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<PersonEntity> list() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PersonEntity.class));
    }

    public void save(PersonEntity person) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("contacts").usingColumns("firstName", "lastName", "patronymic", "birthday", "sex", "partner");
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);
        simpleJdbcInsert.execute(parameterSource);
    }

    public PersonEntity getPerson(int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        Object[] args = {id};
        PersonEntity person = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(PersonEntity.class));
        return person;
    }

    public String inform(PersonEntity person) {
        return null;
    }

    public List<PersonEntity> spouse() {
        String sql = "SELECT * FROM contacts WHERE partner IS NULL";
        List<PersonEntity> personList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PersonEntity.class));
        return personList;
    }

    public void delete(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void saveMarried(int id, int partnerId) {
        var sql = "UPDATE contacts SET partner=? WHERE id=?";
        jdbcTemplate.batchUpdate(sql, List.of(
                new Object[]{id, partnerId},
                new Object[]{partnerId, id}
        ));

    }
}
