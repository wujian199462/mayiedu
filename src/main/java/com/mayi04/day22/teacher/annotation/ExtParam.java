package com.mayi04.day22.teacher.annotation;


import java.lang.annotation.*;

/**
 * 自定义参数注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ExtParam {
    String value();
}
