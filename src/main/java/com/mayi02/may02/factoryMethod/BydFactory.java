package com.mayi02.may02.factoryMethod;

public class BydFactory implements CarFactory {
    @Override
    public Car create(String name) {
        return new BydCar();
    }
}
