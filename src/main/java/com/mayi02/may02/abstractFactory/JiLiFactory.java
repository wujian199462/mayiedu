package com.mayi02.may02.abstractFactory;

public class JiLiFactory implements CarFactory {
    @Override
    public Engine createEngin() {
        return new EngineA();
    }

    @Override
    public Chair createChair() {
        return new ChairA();
    }
}
