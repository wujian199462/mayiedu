package com.mayi04.day22.teacher.sql;

import com.mayi04.day22.teacher.orm.mybatis.aop.MyInvacationHandlerMybatis;

import java.lang.reflect.Proxy;

public class SqlSession {

    //加载Mapper接口
    public static <T> T getMapper(Class clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MyInvacationHandlerMybatis(clazz));
    };
}
