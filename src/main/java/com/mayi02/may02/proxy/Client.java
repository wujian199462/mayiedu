package com.mayi02.may02.proxy;

public class Client {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        UserDaoProxy userDaoProxy =  new UserDaoProxy(userDao);
        userDaoProxy.userAdd();
    }
}
