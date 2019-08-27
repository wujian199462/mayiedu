package com.mayi03.jvm;


//配置推内存大小
public class Test001 {
    public static void main(String[] args) {
        System.out.println("最大内存："+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        System.out.println("可用内存："+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        System.out.println("已经使用内存："+Runtime.getRuntime().totalMemory()/1024/1024+"M");
    }
}
