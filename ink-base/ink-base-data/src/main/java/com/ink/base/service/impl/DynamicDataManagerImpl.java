package com.ink.base.service.impl;

import com.ink.base.service.IDynamicDataManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DynamicDataManagerImpl implements IDynamicDataManager {


    private JdbcTemplate jdbcTemplate;
    @Override
    public List queryData(String sql, String[] values) {

        return jdbcTemplate.queryForList(sql,values);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
