package com.itmayi04.day19.mySpringIOC.service.impl;

import com.itmayi04.day19.mySpringIOC.service.MyUserService;

public class MyUserServiceImpl implements MyUserService {
    @Override
    public void add() {
        System.out.println("利用反射生产。。。");
    }
}
