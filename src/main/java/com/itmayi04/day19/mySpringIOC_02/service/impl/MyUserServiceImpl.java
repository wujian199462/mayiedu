package com.itmayi04.day19.mySpringIOC_02.service.impl;

import com.itmayi04.day19.mySpringIOC_02.annotation.MyResource;
import com.itmayi04.day19.mySpringIOC_02.annotation.MyService;
import com.itmayi04.day19.mySpringIOC_02.service.MyOrderService;
import com.itmayi04.day19.mySpringIOC_02.service.MyUserService;

@MyService
public class MyUserServiceImpl implements MyUserService {

    @MyResource
    private MyOrderService myOrderServiceImpl;
    @Override
    public void add() {
        myOrderServiceImpl.addOrder();
        System.out.println("利用反射生产。。。myUserServiceImpl");
    }
}
