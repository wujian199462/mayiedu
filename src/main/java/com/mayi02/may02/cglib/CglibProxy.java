package com.mayi02.may02.cglib;

import com.mayi02.may02.proxy.UserDaoImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Object targetObject;
    public Object getInstance(Object targetObject){
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开启事务");
        Object invoke = proxy.invoke(targetObject,args);
        System.out.println("提交事务");
        return invoke;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserDaoImpl userDaoImpl = (UserDaoImpl) cglibProxy.getInstance(new UserDaoImpl());
        userDaoImpl.userAdd();
    }
}
