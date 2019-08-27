package com.mayi02.may02.proxy;

//静态代理，静态代理需要生产代理对象
public class UserDaoProxy implements UserDao {
    UserDao userDao;
    UserDaoProxy(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void userAdd() {
        System.out.println("开启事务");
        userDao.userAdd();
        System.out.println("关闭事务");
    }
}
