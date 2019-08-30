package com.mayi04.day22.my.mapper;

import com.mayi04.day22.my.annotation.MyParam;
import com.mayi04.day22.my.annotation.MySelect;

public interface UserMapper {

    @MySelect("insert into user(userName,userAge) values(#{name},#{age})")
    public int addUser(@MyParam("name")String userName,@MyParam("age") Integer age);

}
