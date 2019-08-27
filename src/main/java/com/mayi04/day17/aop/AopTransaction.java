//package com.itmayiedu.aop;
//
//import com.itmayiedu.transaction.TransactionUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//
//@Aspect
//@Component
//public class AopTransaction {
//
//    @Autowired
//    private TransactionUtils transactionUtils;
//
//    //环绕通知
//    @Around("execution(* com.itmayiedu.service.UserService.add(..))")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        //开启事务
//        System.out.println("开启事务");
//        TransactionStatus transactionStatus  = transactionUtils.begin();
//        //执行方法
//        proceedingJoinPoint.proceed();
//        //提交事务
//        System.out.println("关闭事务");
//        transactionUtils.commit(transactionStatus);
//    }
//
//    //异常通知
//    @AfterThrowing("execution(* com.itmayiedu.service.UserService.add(..))")
//    public void afterThrowing(){
//        System.out.println("回滚事务");
//        //获取当前事务，直接回滚
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//    }
//}
