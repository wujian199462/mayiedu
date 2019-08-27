package com.mayi04.day18.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

//编程事务（需要手动去 begin 手动回滚 手动提交）
@Component
@Scope("prototype") //多例 解决线程安全问题
public class TransactionUtils {

    //全局接收事务状态
    private TransactionStatus transactionStatus;

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin(){
        System.out.println("开启事务");
        transactionStatus =dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }
    //提交事务
    public void commit(TransactionStatus transactionStatus){
        System.out.println("提交事务");
        dataSourceTransactionManager.commit(transactionStatus);
    }
    //回滚事务
    public void rollback(){
        System.out.println("回滚事务");
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
