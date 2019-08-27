package com.itmayi04.day18;

import com.itmayi04.day18.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test001 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
