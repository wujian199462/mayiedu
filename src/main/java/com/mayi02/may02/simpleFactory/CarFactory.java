package com.mayi02.may02.simpleFactory;

import org.apache.commons.lang3.StringUtils;

//汽车厂
public class CarFactory {
    public static Car create(String name){
        //判断name是否为空
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        if(name.equals("比亚迪")){
            return new BydCar();
        }
        if(name.equals("吉利")){
            return new JiLiCar();
        }
        //改代码之后，可以不用重启服务器，动态，使用缓存或者数据库+反射。类型id、类型名称类型class地址
        if(name.equals("奥迪")){
            return new JiLiCar();
        }
        if(name.equals("特斯拉")){
            return new JiLiCar();
        }
        //其他或未找到
        return null;
    }
}
