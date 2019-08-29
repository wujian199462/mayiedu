package com.mayi04.day22.teacher.orm.mybatis.aop;

import com.mayi04.day22.teacher.annotation.ExtInsert;
import com.mayi04.day22.teacher.annotation.ExtParam;
import com.mayi04.day22.teacher.annotation.ExtSelect;
import com.mayi04.day22.teacher.orm.utils.JDBCUtils;
import com.mayi04.day22.teacher.utils.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用反射动态代理技术 拦截接口防范
 */
public class MyInvacationHandlerMybatis implements InvocationHandler {
    private Object object;

    public MyInvacationHandlerMybatis(Object object) {
        this.object = object;
    }

    //proxy 代理对象 method 拦截方法 args 方法上的参数值
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用动态代理技术拦截接口方法");
        //白话文翻译，@ExtInsert封装过程
        //1.判断方法上是否存在ExtInsert
        ExtInsert extInsert = method.getDeclaredAnnotation(ExtInsert.class);
        if(extInsert!=null){
            return extInert(extInsert,proxy,method,args);
        }
        //2.查询思路
        //1.判断方法上是否存在注解
        ExtSelect extSelect = method.getDeclaredAnnotation(ExtSelect.class);
        if(extSelect!=null){
            //2.获取查询语句
            String selectSQL = extSelect.value();
            //3.获取方法上的参数，绑定在一起
            ConcurrentHashMap<Object,Object> paramsMap = paramsMap(proxy,method,args);
            //4.替换成?传递方式
            List<String> sqlSelectparameter = SQLUtils.sqlSelectParameter(selectSQL);
            //传递参数
            List<Object> sqlParams = new ArrayList<>();
            for(String parameterName:sqlSelectparameter){
                Object parameterValue = paramsMap.get(parameterName);
                sqlParams.add(parameterValue);
            }
            //将sql语句替换成？
            String newSql = SQLUtils.parameQuestion(selectSQL,sqlSelectparameter);
            System.out.println("newSQL="+newSql+",sqlParams="+sqlParams.toString());

            //6.使用反射机制实例化对象 ## 获取方法返回的类型，进行实例化
            //思路
            // 1.使用反射机制获取方法的类型
            //2.判断是否有结果集，如果有结果集，在进行初始化
            //3.使用反射机制，给对象复制
            ResultSet res = JDBCUtils.query(newSql,sqlParams);
            if(!res.next()){
                return null;
            }
            //下标往上移动一位
            res.previous();
            Class<?> returnType = method.getReturnType();
            Object object = returnType.newInstance();
            while(res.next()){
                Field[] fields = returnType.getDeclaredFields();
                for(Field field : fields){
                    String fieldName = field.getName();
                    Object fieldValue = res.getObject(fieldName);
                    field.setAccessible(true);
                    field.set(object,fieldValue);
                }

              /*  for(String parameteName : sqlSelectparameter){
                    //获取参数值
                    Object resultValue = res.getObject(parameteName);
                    Field field = returnType.getDeclaredField(parameteName);
                    //允许私有访问
                    field.setAccessible(true);
                    field.set(object,resultValue);
                }*/
            }
            return object;
        }



        return null;
    }


    private Object extInert(ExtInsert extInsert,Object proxy, Method method, Object[] args){
        //2.获取SQL语句，获取注解Insert语句
        String insertSql= extInsert.value();
        System.out.println("insertSql="+insertSql);
        //3.获取方法的参数和sql参数进行匹配
        //定义一个map集合key为 @ExtParam 的value,value 结果为参数值
        ConcurrentHashMap<Object,Object> paramsMap = paramsMap(proxy,method,args);
        //存放sql执行参数

        String [] sqlInsertParams = SQLUtils.sqlInsertParameter(insertSql);
        List<Object> sqlParams = sqlParams(sqlInsertParams,paramsMap);
        //4.替换参数变为？
        String newSQL = SQLUtils.parameQuestion(insertSql,sqlInsertParams);
        System.out.println("newSQL="+newSQL);
        //5.调用jdbc底层代码执行语句
        return JDBCUtils.insert(newSQL,false,sqlParams);
    }

    private List<Object> sqlParams(String [] sqlInsertParams,ConcurrentHashMap<Object,Object> paramsMap){
        List<Object> sqlParams = new ArrayList<>();
        for(String paranName : sqlInsertParams){
            Object paramValue =  paramsMap.get(paranName);
            sqlParams.add(paramValue);
        }
        return sqlParams;
    }


    private ConcurrentHashMap<Object,Object> paramsMap(Object proxy, Method method, Object[] args){
        ConcurrentHashMap<Object,Object> paramsMap = new ConcurrentHashMap<Object,Object>();
        //获取方法上的参数
        Parameter[] parameters = method.getParameters();
        for(int i= 0;i<parameters.length;i++){
            Parameter parameter = parameters[i];
            ExtParam extParam = parameter.getDeclaredAnnotation(ExtParam.class);
            if(extParam != null){
                //参数名称
                String paramName = extParam.value();
                Object paramValue = args[i];
                System.out.println(paramName+","+paramValue);
                paramsMap.put(paramName,paramValue);
            }
        }
        return paramsMap;
    }




    public Object extInserSql(){
        return object;
    }
}
