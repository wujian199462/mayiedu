package com.mayi04.day22.my;

import com.mayi04.day22.my.mapper.UserMapper;
import com.mayi04.day22.my.sql.SqlSession;

public class MyTestInsert {
    public static void main(String[] args) {
        UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
        userMapper.addUser();
    }
}
