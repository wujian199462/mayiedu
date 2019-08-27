package com.mayi04.day18.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name,Integer age){
        String sql = "INSERT INTO t_user (NAME,AGE)values(?,?)";
        int updateResult = jdbcTemplate.update(sql,name,age);
        System.out.println("updateResult:"+updateResult);
    }
}
