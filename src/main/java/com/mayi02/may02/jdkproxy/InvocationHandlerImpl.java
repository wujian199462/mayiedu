package com.mayi02.may02.jdkproxy;

import mayi02.may02.proxy.UserDao;
import mayi02.may02.proxy.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//每次生成动态代理对象时，实现了Invocationhandler接口的调用处理器对象
public class InvocationHandlerImpl implements InvocationHandler {
    private Object target;//目标代理对象
    public InvocationHandlerImpl(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //开启事务
        System.out.println("动态代理-开启事务");
        Object invok = method.invoke(target,args);
        System.out.println("动态代理-提交事务");
        return invok;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        InvocationHandlerImpl invocationHandlerImpl = new InvocationHandlerImpl(userDao);
        ClassLoader classLoader = userDao.getClass().getClassLoader();
        Class<?>[] interfaces = userDao.getClass().getInterfaces();
        //调用动态代理实例
        UserDao userDao1 = (UserDao) Proxy.newProxyInstance(classLoader,interfaces,invocationHandlerImpl);
        userDao1.userAdd();
    }
}
