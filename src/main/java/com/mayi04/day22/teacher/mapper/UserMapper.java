package com.mayi04.day22.teacher.mapper;

import com.mayi04.day22.teacher.annotation.ExtInsert;
import com.mayi04.day22.teacher.annotation.ExtParam;
import com.mayi04.day22.teacher.annotation.ExtSelect;
import com.mayi04.day22.teacher.entity.User;

public interface UserMapper {

    @ExtInsert("insert into user(userName,userAge) values(#{userName},#{userAge})")
    public int inserUser(@ExtParam("userName") String userName,@ExtParam("userAge") Integer userAge);

    @ExtSelect("select * from User where userName=#{userName} and userAge=#{userAge} ")
    User selectUser(@ExtParam("userName") String name, @ExtParam("userAge") Integer userAge);

}
