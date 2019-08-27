//package com.itmayiedu.transaction;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
////编程事务（需要手动去 begin 手动回滚 手动提交）
//@Component
//public class TransactionUtils {
//
//    //获取事务源
//    @Autowired
//    private DataSourceTransactionManager dataSourceTransactionManager;
//
//    //开启事务
//    public TransactionStatus begin(){
//        TransactionStatus transaction =dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
//        return transaction;
//    }
//    //提交事务
//    public void commit(TransactionStatus transactionStatus){
//        dataSourceTransactionManager.commit(transactionStatus);
//    }
//    //回滚事务
//    public void rollback(TransactionStatus transactionStatus){
//        dataSourceTransactionManager.rollback(transactionStatus);
//    }
//}
