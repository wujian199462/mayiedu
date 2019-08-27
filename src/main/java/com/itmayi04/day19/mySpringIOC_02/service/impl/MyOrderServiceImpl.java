package com.itmayi04.day19.mySpringIOC_02.service.impl;

import com.itmayi04.day19.mySpringIOC_02.annotation.MyService;
import com.itmayi04.day19.mySpringIOC_02.service.MyOrderService;

@MyService
public class MyOrderServiceImpl implements MyOrderService {
    @Override
    public void addOrder() {
        System.out.println("add myOrder");
    }
}
