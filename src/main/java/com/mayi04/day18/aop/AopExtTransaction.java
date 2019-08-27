package com.mayi04.day18.aop;

import com.mayi04.day18.annotation.ExtTransaction;
import com.mayi04.day18.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

//自定义事务注解具体实现
@Aspect
@Component
public class AopExtTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    //使用异常通知进行事务回滚
    @AfterThrowing("execution(* com.itmayiedu0418.service.*.*.*(..))")
    public void afterThrowing(){
        //获取当前事务进行回滚
       // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       //判断异常方法是否有事务注解，如果有事务注解就回滚
        transactionUtils.rollback();
    }

    //环绕通知
    @Around("execution(* com.itmayiedu0418.service.*.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        //1.获取该方法是否加上注解
        ExtTransaction extTransaction = getMethodExtTransaction(pjp);
        TransactionStatus transactionStatus = begin(extTransaction);
        //3.调用目标代理对象方法
        pjp.proceed();
        //4.判断该方法上是否有注解
        commit(transactionStatus);
    }

    private TransactionStatus begin(ExtTransaction extTransaction){
        if(extTransaction == null){
            return null;
        }
        //2.如果存在事务注解，开启事务
        return transactionUtils.begin();
    }

    private void commit(TransactionStatus transactionStatus){
        if(transactionStatus != null){
            //5.如果存在事务注解，提交事务
            transactionUtils.commit(transactionStatus);
        }
    }
    //获取方法上是否有事务注解
    private ExtTransaction getMethodExtTransaction(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        //获取目标对象
        Class classTarget = pjp.getTarget().getClass();
        //获取目标对象类型
        Class[] par = ((MethodSignature)pjp.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName,par);
        ExtTransaction extTransaction = objMethod.getDeclaredAnnotation(ExtTransaction.class);
        return extTransaction;
    }
}
