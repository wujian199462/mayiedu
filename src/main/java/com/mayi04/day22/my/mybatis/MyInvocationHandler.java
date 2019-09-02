package com.mayi04.day22.my.mybatis;

import com.mayi04.day22.my.annotation.MyInsert;
import com.mayi04.day22.my.annotation.MyParam;
import com.mayi04.day22.my.annotation.MySelect;
import com.mayi04.day22.my.util.JDBCUtils;
import com.mayi04.day22.my.util.Myutils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyInvocationHandler implements InvocationHandler {

    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("my 动态代理");
        //1.判断该方法上是否有注解
        MyInsert myInsert = method.getDeclaredAnnotation(MyInsert.class);
        MySelect mySelect = method.getDeclaredAnnotation(MySelect.class);
        String oldSql = "";
        //插入
        if(myInsert != null){
            oldSql = myInsert.value();
            System.out.println(oldSql);
        }
        //查询
        if(mySelect != null){
            oldSql = mySelect.value();
            System.out.println(oldSql);
        }
        //2.获取sql语句
        //3.获取参数上的注解 的参数 用map 存起来key 为注解的值 value为参数的值
        ConcurrentHashMap<Object,Object> paramsMap = new ConcurrentHashMap<Object,Object>();
        Parameter[] parameters = method.getParameters();
        for(int i=0;i<parameters.length;i++){
            Parameter parameter = parameters[i];
            MyParam myParam = parameter.getDeclaredAnnotation(MyParam.class);
            if(parameter != null){
                String paramName = myParam.value();
                Object paramValue = args[i];
                System.out.println(paramName+":"+paramValue);
                paramsMap.put(paramName,paramValue);
            }
        }
        //4.获取传入的List 根据原先的sql里面的#{} 获得map里面的值用list记录
        List<Object> sqlParam = new ArrayList<Object>();
        //sql语句里面参数的集合
        List<String> paramList = Myutils.getParams(oldSql);

        //5.将原sql用？代替生产newsql
        String newSql = Myutils.getNewSql(oldSql,paramList);
        System.out.println(newSql);
        //获取参数的list
        List<Object> parms = new ArrayList<>();
        for(int i=0;i<paramList.size();i++){
            parms.add(paramsMap.get(paramList.get(i)));
        }
        //6.执行sql 返回结果
        int i =  JDBCUtils.insert(newSql,parms);

        return i;
    }


}
