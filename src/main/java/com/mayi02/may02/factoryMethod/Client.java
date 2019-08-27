package com.mayi02.may02.factoryMethod;

public class Client {
    public static void main(String[] args) {
        CarFactory carFactory = new BydFactory();
        Car car = carFactory.create(null);
        car.run();
    }
}
