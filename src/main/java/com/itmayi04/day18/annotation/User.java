package com.itmayi04.day18.annotation;

import java.lang.reflect.Method;

public class User {
    @AddAnnotation(userName = "张三",userId = 18,arrays = {"1"})
    public void add(){}

    public void del(){}

    public static void main(String[] args) throws ClassNotFoundException {
        //怎么获取到方法上注解信息 反射机制
        Class forName = Class.forName("com.itmayi04.day18.annotation.User");
       //获取当前类（不包含继承类）所有的方法
       Method[] declareMathods =  forName.getDeclaredMethods();
       for(Method method:declareMathods){
           //获取该方法上是否存在注解
           AddAnnotation addAnnotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if(addAnnotation==null){
                System.out.println(method.getName());
                System.out.println("该方法上没有注解");
                //该方法上没有注解
                continue;
            }
            //在该方法上查找到该注解
           System.out.println("userId="+addAnnotation.userId());
           System.out.println("userName:"+addAnnotation.userName());
           System.out.println("arrays="+addAnnotation.arrays());
       }
    }
}
