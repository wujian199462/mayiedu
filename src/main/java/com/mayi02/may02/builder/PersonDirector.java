package com.mayi02.may02.builder;

//构建任务,整合所有部件
public class PersonDirector {

    public Person createPerson(PersonBuilder personBuilder){
        personBuilder.builderHeade();
        personBuilder.builderBody();
        personBuilder.builderFoot();
        return personBuilder.builderPerson();
    }

    public static void main(String[] args) {
        PersonDirector personDirector = new PersonDirector();
        Person person = personDirector.createPerson(new ManBuilder());
        System.out.println(person.toString());

    }
}

