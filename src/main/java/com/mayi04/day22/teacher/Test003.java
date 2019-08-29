package com.mayi04.day22.teacher;

import com.mayi04.day22.teacher.entity.User;
import com.mayi04.day22.teacher.mapper.UserMapper;
import com.mayi04.day22.teacher.sql.SqlSession;

public class Test003 {
    public static void main(String[] args) {
        //使用动态代理技术虚拟调用接口
       UserMapper userMapper =  SqlSession.getMapper(UserMapper.class);
       //先走invoke
      // userMapper.inserUser("111",11);
        User user =  userMapper.selectUser("wujian",20);
        System.out.println(user.getUserName());
    }
}
