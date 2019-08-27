package com.mayi03.jvm;

//栈溢出
public class Test005 {

    private static int count;
    public static void count(){
        try {
            count++;
            count();
        } catch (Throwable e) {
            System.out.println("最大深度:"+count);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        count();
    }

}
