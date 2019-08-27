package com.itmayi04.day19.P1;

import com.itmayi04.day18.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test001 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring02.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
    }
}
