package com.itmayi04.day20.springmvc.controller;

import com.itmayi04.day20.springmvc.annotation.ExtController;
import com.itmayi04.day20.springmvc.annotation.ExtRequestMapping;

@ExtController
@ExtRequestMapping("/ext")
public class ExtIndexController {

    @ExtRequestMapping("/test")
    public String test(){
        System.out.println("手写springmvc 框架");
        return "index";
    }
}
