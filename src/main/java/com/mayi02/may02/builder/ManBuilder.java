package com.mayi02.may02.builder;

//构造人物 外国人
public class ManBuilder implements PersonBuilder {
    Person person;

    public ManBuilder() {
        person =  new Person();
    }

    @Override
    public void builderHeade() {
        person.setHead("美国人头部： 鼻子坚，长脸，蓝眼睛");
    }

    @Override
    public void builderBody() {
        person.setBody("美国人体部：长的比较高，块头大");
    }

    @Override
    public void builderFoot() {
        person.setFoot("美国人四肢:腿长。。。");
    }
    @Override
    public Person builderPerson(){
        return person;
    }

}
