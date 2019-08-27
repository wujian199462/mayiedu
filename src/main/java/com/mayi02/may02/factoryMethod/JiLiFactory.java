package com.mayi02.may02.factoryMethod;

//吉利汽车店
public class JiLiFactory implements CarFactory {
    @Override
    public Car create(String name) {
        return new JiLiCar();
    }
}
