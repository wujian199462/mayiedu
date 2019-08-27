package com.mayi03.loadClass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Hotswap {
    /*public static void main(String[] args) throws InterruptedException {
        System.out.println("开始加载user1.0版本");
        User user1 = new User();
        user1.add();

        Thread.sleep(15*1000);
        System.out.println("开始加载user2.0版本");
        User user2 = new User();
        user2.add();
    }*/
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        loadUser();
        System.gc();

        //需要被修改的class文件
        File file1 = new File("F:\\test\\User.class");
        //之前的class文件
        File file2 = new File("D:\\ideaProjects\\mayi\\out\\production\\classes\\mayi03\\loadClass\\User.class");
        //删除之前的class 文件
        boolean isDelete = file2.delete();
        if(!isDelete){
            System.out.println("热部署失败，无法删除文件。。。");
            return;
        }
        file1.renameTo(file2);//移动到file2目录
      //  Thread.sleep(15*1000);
        System.out.println("开始加载user2.0版本");
        loadUser();
    }

    public static void loadUser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        //使用类加载器读取信息
        Class findClass = myClassLoader.findClass("mayi03.loadClass.User");
        //使用反射机制初始化对象
        Object object = findClass.newInstance();
        //使用反射机制调用方法
        Method method = findClass.getMethod("add");
        method.invoke(object);
        System.out.println(object.getClass());
        System.out.println(object.getClass().getClassLoader());
    }
}
