package com.mayi02.may02.abstractFactory;

//包装厂
public interface CarFactory {
    //创建发动机
    Engine createEngin();
    //创建座椅
    Chair createChair();
}
