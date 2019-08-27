package com.mayi02.may02.builder;

//创建人体Builder  在使用设计模式的时候一定要学会使用抽象类或者接口
public interface PersonBuilder {
    //构造头部
    void builderHeade();
    //构造体部
    void builderBody();
    //构造四肢
    void builderFoot();
    //组装部件
    Person builderPerson();
}
