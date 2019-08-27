package com.itmayi04.day18.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//约束
@Target(value = ElementType.METHOD)
//生命周期
@Retention(RetentionPolicy.RUNTIME)

//@interface 定义注解
public @interface AddAnnotation {

    //手写Spring事务注解
    int userId() default 0;

    String userName() default "默认名称";

    String [] arrays();
}
