package com.itmayi04.day18.service.impl;

import com.itmayi04.day18.dao.UserDao;
import com.itmayi04.day18.service.LogService;
import com.itmayi04.day18.service.UserService;
import com.itmayi04.day18.transaction.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//user 服务员层
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LogService logService;
    @Autowired
    private TransactionUtils transactionUtils;
//    @Override
//    public void add() {
//        TransactionStatus transactionStatus = null;
//        try {
//            //开启事务
//            transactionStatus = transactionUtils.begin();
//            userDao.add("test001",20);
//            int i=1/0;
//            System.out.println("#####往数据库添加数据。。。");
//            userDao.add("test002",21);
//            //提交事务
//            transactionUtils.commit(transactionStatus);
//        }catch (Exception e) {
//            e.getMessage();
//            //回滚事务
//            if(transactionStatus != null)
//                transactionUtils.rollback(transactionStatus);
//        }
//    }


    //声明 @Transactional 或者XML方式
    //方法执行前，开启提交事务
//    @Override
//    @Transactional
//    public void add() {
//        try {
//            userDao.add("test001", 20);
//            int i = 1 / 0;
//            System.out.println("#####往数据库添加数据。。。");
//            userDao.add("test002", 21);
//        }catch (Exception e){
//
//        }
//    }
//    //方法结束提交事务


    //@Override
    //@ExtTransaction
    @Transactional
    public void add() {
        logService.addLog();//后面程序发生错误，不能影响到我的回滚###，正常情况当addLog方法执行完毕，就应该提交事务
        userDao.add("test001", 20);
      //  int i= 1/0;
        System.out.println("#####往数据库添加数据。。。");
        userDao.add("test002", 21);
    }
}
