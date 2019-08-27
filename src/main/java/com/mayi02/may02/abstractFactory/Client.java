package com.mayi02.may02.abstractFactory;

public class Client {
    public static void main(String[] args) {
        CarFactory carFactory = new JiLiFactory();
        Engine engine = carFactory.createEngin();
        Chair chair = carFactory.createChair();
        engine.run();
        chair.run();
    }
}
