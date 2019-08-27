package com.mayi03.javaSister;

import java.io.IOException;

//使用java字节码技术创建字节码
public class Test002 {

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //1.创建user类
        CtClass userClass = pool.makeClass("mayi03.javaSister.USer");
        //2.创建name 和age属性
        CtField nameField = CtField.make(" private String name;",userClass);
        CtField ageFiel = CtField.make(" private String age;",userClass);
        //3.添加属性
        userClass.addField(nameField);
        userClass.addField(ageFiel);
        //4.创建方法
        CtMethod getNameMethod = CtMethod.make("public String getName(){return name;}",userClass);
        //5.添加方法
        userClass.addMethod(getNameMethod);
        //添加构造函数
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String"),pool.get("java.lang.Integer")},userClass);
        ctConstructor.setBody("{this.name = name;this.age = age;}");
        userClass.addConstructor(ctConstructor);

        //生成class文件
        userClass.writeFile("F:/test");;
    }

}
