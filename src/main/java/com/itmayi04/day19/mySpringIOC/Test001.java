package com.itmayi04.day19.mySpringIOC;

import com.itmayi04.day19.mySpringIOC.service.MyUserService;
import com.itmayi04.day19.mySpringIOC.spring.MyClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test001 {

    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContext app = new MyClassPathXmlApplicationContext("mySpring.xml");
        MyUserService myUserService = (MyUserService) app.getBean("myUserService");
        myUserService.add();
    }
}
