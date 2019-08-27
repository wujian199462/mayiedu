package com.mayi02.may02.abstractFactory;

//发动机
public interface Engine {
    void run();
}

class EngineA implements Engine{
    @Override
    public void run() {
        System.out.println("发动机转速快。");
    }
}

class EnginB implements Engine{
    @Override
    public void run() {
        System.out.println("发动机转速慢。");
    }
}
