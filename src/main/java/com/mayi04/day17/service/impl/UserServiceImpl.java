//package com.itmayiedu.service.impl;
//
//import com.itmayiedu.dao.UserDao;
//import com.itmayiedu.service.UserService;
//import com.itmayiedu.transaction.TransactionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.annotation.Transactional;
//
////user 服务员层
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private TransactionUtils transactionUtils;
////    @Override
////    public void add() {
////        TransactionStatus transactionStatus = null;
////        try {
////            //开启事务
////            transactionStatus = transactionUtils.begin();
////            userDao.add("test001",20);
////            int i=1/0;
////            System.out.println("#####往数据库添加数据。。。");
////            userDao.add("test002",21);
////            //提交事务
////            transactionUtils.commit(transactionStatus);
////        }catch (Exception e) {
////            e.getMessage();
////            //回滚事务
////            if(transactionStatus != null)
////                transactionUtils.rollback(transactionStatus);
////        }
////    }
//
//
//    @Override
//    @Transactional
//    public void add() {
//        userDao.add("test001",20);
//        int i=1/0;
//        System.out.println("#####往数据库添加数据。。。");
//        userDao.add("test002",21);
//    }
//}
