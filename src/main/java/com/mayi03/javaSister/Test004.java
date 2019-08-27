package com.mayi03.javaSister;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态修改字节码文件
public class Test004 {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();
        CtClass userClass = pool.get("mayi03.javaSister.USer");
        CtMethod method =new CtMethod(CtClass.voidType,"sum",new CtClass[]{CtClass.intType,CtClass.intType},userClass);
        method.setBody("{System.out.println(\"sum=\"+($1+$2));}");
        //添加方法
        userClass.addMethod(method);
        userClass.writeFile("F:/test");

        //动态执行方法
        Class clazz = userClass.toClass();
        Object newInstance = clazz.newInstance();
        Method sumMethod =  clazz.getDeclaredMethod("sum",int.class,int.class);
        sumMethod.invoke(newInstance,1,5);
    }
}
