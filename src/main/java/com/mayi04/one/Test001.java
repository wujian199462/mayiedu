package com.mayi04.one;

import com.mayi04.one.proxy.UserServiceProxy;
import com.mayi04.one.service.UserService;
import com.mayi04.one.service.impl.UserServiceImpl;

public class Test001 {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.add();
    }
}
