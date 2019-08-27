package com.mayi04.one.proxy;

import com.mayi04.one.service.UserService;

public class UserServiceProxy implements UserService{
    private UserService userService;
    public UserServiceProxy(UserService userService){
        this.userService = userService;
    }

    public void add(){
        System.out.println("静态代理--开启事务");
        userService.add();
        System.out.println("静态代理--提交事务");
    }
}
