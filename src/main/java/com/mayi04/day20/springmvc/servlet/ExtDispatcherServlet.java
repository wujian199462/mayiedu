package com.mayi04.day20.springmvc.servlet;

import com.mayi04.day20.springmvc.annotation.ExtController;
import com.mayi04.day20.springmvc.annotation.ExtRequestMapping;
import com.mayi04.day20.springmvc.utils.ClassUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义前端控制器
 *  *
 *  * 手写springmvc
 *  * 1。创建一个前端控制器（）ExtDispatcherServlet 拦截所有请求（springmvc 基于servlet实现）
 *  * 2.初始化操作 重写servlet init 方法
 *  *   2.1 将扫包范围内所有的类，注入到springmvc容器中，存放到Map集合中 key为默认类名小写，value 对象
 *  *   2.2 将url映射和方法进行关联
 *  *      2.2.1 判断类上是否有注解，使用java反射机制循环遍历方法，判断方法是否存在注解，进行封装url和方法对应存入集合中
 *  * 3.处理请求 重写Get或者是Post方法
 *  *   3.1 获取请求url,去urlBeans集合中获取对象实例对象，获取成功实例对象后，调用urlMethods集合获取方法名称，使用反射机制执行
 *  *
 */
public class ExtDispatcherServlet extends HttpServlet {

    //springmvc 容器对象 key:类名id,value 对象
    private ConcurrentHashMap<String,Object> springmvcBeans = new ConcurrentHashMap<String, Object>();
    //springmvc 容器对象 请求地址，value 类
    private ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<String, Object>();
    //springmvc 容器对象 请求地址，value 方法名称
    private ConcurrentHashMap<String,String> urlMethods = new ConcurrentHashMap<String, String>();
    @Override
    public void init() throws ServletException {
        //1.获取当前包下所有的类
        List<Class<?>> classes = ClassUtil.getClasses("com.itmayi04.day20.springmvc.controller");
        //2 将扫包范围内所有的类，注入到springmvc容器中，存放到Map集合中 key为默认类名小写，value 对象
        try {
            findClasseMVCAnnotation(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.将url映射和方法进行关联,解析类和
        handleMapping();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //#############处理请求#########
        //1.获取亲求url地址
        String requestURI = req.getRequestURI();
        if(StringUtils.isEmpty(requestURI)){
            return;
        }
        //2.从Map集合中获取控制对象
        Object object = urlMethods.get(requestURI);
        if(object==null){
            resp.getWriter().println("not find 404 url");
            return;
        }
        //3。使用url地址获取方法
        String methodName = urlMethods.get(requestURI);
        if(StringUtils.isEmpty(methodName)){
            resp.getWriter().println("not found method");
        }
        //4。使用java的反射机制调用方法
        String resultPage = (String) methodInvoke(object,methodName);
        resp.getWriter().println(resultPage);
        //5.是否Java的反射机制获取方法返回结果
        //6.调用试图转换器渲染给页面展示
    }

    private Object methodInvoke(Object object,String methodName){
        try {
            Class<?> classInfo = object.getClass();
            Method method = classInfo.getMethod(methodName);
            Object result = method.invoke(object);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //2 将扫包范围内所有的类，注入到springmvc容器中，存放到Map集合中 key为默认类名小写，value 对象
    public void findClasseMVCAnnotation(List<Class<?>> classes) throws IllegalAccessException, InstantiationException {
        for(Class<?> classInfo : classes){
            ExtController extController =  classInfo.getAnnotation(ExtController.class);
            if(extController != null){
                //默认类名小写
                String beanId = ClassUtil.toLowerCaseFirstOne(classInfo.getSimpleName());
                //实例化对象
                Object object = ClassUtil.newInstance(classInfo);
                springmvcBeans.put(beanId,object);
            }
        }
    }

    //3.将url映射和方法进行关联,解析类和
    public void handleMapping(){
        //1.遍历springmvc bean容器 获取类上是否有url映射注解
        for(Map.Entry<String,Object> mvcBean: springmvcBeans.entrySet()){
           //获取bean对象
            Object object = mvcBean.getValue();
            //判断类上是否有url注解
            Class<? extends Object> classInfo = object.getClass();
            ExtRequestMapping declaredAnnotation = classInfo.getAnnotation(ExtRequestMapping.class);
            String baseUrl = "";
            if(declaredAnnotation!=null){
                //获取类上的url映射地址
                baseUrl = declaredAnnotation.value();
            }
            //3.遍历所有的方法上是否有url映射注解
            Method[] declaredMethods = classInfo.getDeclaredMethods();
            for(Method method : declaredMethods){
                //判断方法上是否有url 映射注解
                ExtRequestMapping methodExtRequestMapping = method.getDeclaredAnnotation(ExtRequestMapping.class);
                if(methodExtRequestMapping!=null){
                    String methodUrl = baseUrl + methodExtRequestMapping.value();
                    //springmvc 容器对象 请求地址，value 类
                    urlBeans.put(methodUrl,object);
                    //springmvc 容器对象 请求地址，value 方法名称
                    urlMethods.put(methodUrl,method.getName());
                }
            }
        }

    }
}
