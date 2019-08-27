package com.mayi03.rubbish;

//演示垃圾回收机制
public class Test003 {

    //什么是不可达对象：没有被继续引用 没有存活 没有被继续使用
    public static void main(String[] args) {
        Test003 obj1 = new Test003();//可达
        obj1 = null;//不可达 提示jvm回收
       // Object obj2 = obj1;
        System.gc();//提示gc进行回收垃圾。误区：提示给jvm垃圾回收机制进行回收，但是不代表立即进行回收
        //gc线程是守护线程
    }

    // finalize object
    @Override
    protected void finalize() throws Throwable {
        //垃圾回收机制之前回进行执行的方法（）
        System.out.println("垃圾回收机制要开始执行我的方法了。。。");
    }
}
