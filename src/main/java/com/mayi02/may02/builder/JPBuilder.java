package com.mayi02.may02.builder;

//构建日本人
public class JPBuilder implements PersonBuilder {
    Person person;

    public JPBuilder() {
        person = new Person();
    }

    @Override
    public void builderHeade() {
        person.setHead("日本人头部：圆脸");
    }

    @Override
    public void builderBody() {
        person.setBody("日本人体部：比较矮");
    }

    @Override
    public void builderFoot() {
        person.setFoot("日本人四肢腿短");
    }
    @Override
    public Person builderPerson(){
        return person;
    }
}
