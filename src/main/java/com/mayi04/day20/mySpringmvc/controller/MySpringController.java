package com.mayi04.day20.mySpringmvc.controller;

import com.mayi04.day20.mySpringmvc.annotation.MyController;
import com.mayi04.day20.mySpringmvc.annotation.MyRequestMapping;

@MyController
@MyRequestMapping("/my")
public class MySpringController {

    @MyRequestMapping("/index")
    public String test(){
        System.out.println("自己");
        return "index";
    }
}
