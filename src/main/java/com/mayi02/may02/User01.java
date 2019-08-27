package com.mayi02.may02;

//懒汉式
public class User01 {
    //饿汉式  天生线程安全
    private static User01 user01 = new User01();
    private User01(){};
    public User01 getInstance(){
        return user01;
    }
}
