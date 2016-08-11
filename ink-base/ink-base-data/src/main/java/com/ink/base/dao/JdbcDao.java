package com.ink.base.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcDao extends JdbcTemplate {

    @Override
    protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
        if(requiredType.getName().startsWith("java.lang")){
            return super.getSingleColumnRowMapper(requiredType);
        }
        // 解决queryForList只支持单一列的问题
        return new BeanPropertyRowMapper<T>(requiredType);
    }
}