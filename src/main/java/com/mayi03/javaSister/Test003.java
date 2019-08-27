package com.mayi03.javaSister;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test003 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //1.利用放射技术执行某方法
        Class<?> forName = Class.forName("mayi03.javaSister.Test003");
        Object newInstance = forName.newInstance();
        Method method =  forName.getDeclaredMethod("sum",int.class,int.class);
        method.invoke(newInstance,1,3);
    }

    public void sum(int a,int b){
        System.out.println("sum="+(a+b));
    }
}
