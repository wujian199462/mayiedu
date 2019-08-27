package com.mayi04.day19.P1;

import com.mayi04.day18.service.UserService;
import com.mayi04.day19.P1.spring.ExtClassPathXmlApplicationContext;

//测试手写SpringIOC代买
public class Test002 {
    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext applicationContext = new ExtClassPathXmlApplicationContext("spring02.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
    }
}
