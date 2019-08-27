package com.itmayi04.day19.P2;

import com.itmayi04.day19.P2.service.UserService;
import com.itmayi04.day19.P2.spring.ext.ExtClassPathXmlApplicationContext;

public class Test001 {

    //1.在使用注解版本事务的时候，你们第一步做什么？扫包
    public static void main(String[] args) throws Exception {
        //1.使用java的反射机制扫包，获取当前报下所有的类
        //2。判断该类上是否存在注入bean的注解
        //3.使用Java的反射机制进行初始化
      ExtClassPathXmlApplicationContext app = new  ExtClassPathXmlApplicationContext("com.itmayi04.day19.P2");
      UserService userService = (UserService) app.getBean("userServiceImpl");
        System.out.println(userService);
        userService.add();
    }
}
