package com.example.table.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonRepositoryExtImpl implements PersonRepositoryExt {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void setMarriedExt(Integer partnerId, Integer id) {
        var sql = "UPDATE contacts SET partner_id=? WHERE id=?";
        jdbcTemplate.batchUpdate(sql, List.of(
                new Object[]{id, partnerId},
                new Object[]{partnerId, id}
        ));
    }

}
