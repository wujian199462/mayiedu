package com.mayi02.may02.simpleFactory;

public class Client {
    public static void main(String[] args) {
        Car byd = CarFactory.create("吉利");
        byd.run();
    }
}
