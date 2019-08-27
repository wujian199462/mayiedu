package com.mayi04.day19.mySpringIOC_02;

import com.mayi04.day19.mySpringIOC_02.service.MyUserService;
import com.mayi04.day19.mySpringIOC_02.spring.MyAnnotationClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) throws Exception {
        MyAnnotationClassPathXmlApplicationContext app = new MyAnnotationClassPathXmlApplicationContext("com.itmayi04.day19.mySpringIOC_02.service.impl");
        MyUserService myUserService = (MyUserService) app.getBean("myUserServiceImpl");
        myUserService.add();
    }
}
