package com.mayi04.day19.P2.service.impl;

import com.mayi04.day19.P2.service.OrderService;
import com.mayi04.day19.P2.service.UserService;
import com.mayi04.day19.P2.spring.extannotation.ExtResource;
import com.mayi04.day19.P2.spring.extannotation.ExtService;

//user 服务员层
@ExtService
public class UserServiceImpl implements UserService {

    @ExtResource
    private OrderService orderServiceImpl;

    public void add() {
        orderServiceImpl.addOrder();
        System.out.println("使用java 反射机制初始化对象");
    }
}
