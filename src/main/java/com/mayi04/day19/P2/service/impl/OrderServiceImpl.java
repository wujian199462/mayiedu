package com.mayi04.day19.P2.service.impl;

import com.mayi04.day19.P2.service.OrderService;
import com.mayi04.day19.P2.spring.extannotation.ExtService;

@ExtService
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder() {
        System.out.println("add order");
    }
}
