package com.mayi03.loadClass;

import java.io.InputStream;

public class MyClassLoader extends ClassLoader{

    //传递一个class路径地址
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //1.获取文件名称
            String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
            //2.读取文件流
            InputStream is = this.getClass().getResourceAsStream(filename);
            //3.将读取byte数组给jvm识别class对象
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            //4.读取byte数组给jvm识别class对象
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){
            throw new ClassNotFoundException();
        }
    }
}
