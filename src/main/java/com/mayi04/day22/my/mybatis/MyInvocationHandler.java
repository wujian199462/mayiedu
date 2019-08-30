package com.mayi04.day22.my.mybatis;

import com.mayi04.day22.my.annotation.MyInsert;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("my 动态代理");
        //1.判断该方法上是否有注解
        MyInsert myInsert = method.getDeclaredAnnotation(MyInsert.class);
        String oldSql = "";
        if(myInsert != null){
            oldSql = myInsert.value();
            System.out.println(oldSql);
        }

        //2.获取sql语句
        //3.获取参数上的注解 的参数 用map 存起来key 为注解的值 value为参数的值
        //4.获取传入的List 根据原先的sql里面的#{} 获得map里面的值用list记录
        //5.将原sql用？代替生产newsql
        //6.执行sql 返回结果

        return null;
    }


}
