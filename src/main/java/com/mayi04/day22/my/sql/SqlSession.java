package com.mayi04.day22.my.sql;

import com.mayi04.day22.my.mybatis.MyInvocationHandler;

import java.lang.reflect.Proxy;

public class SqlSession {
    public static  <T>T getMapper(Class clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MyInvocationHandler(clazz));
    }
}
