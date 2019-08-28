package com.mayi04.day20.mySpringmvc.servlet;

import com.mayi04.day20.mySpringmvc.annotation.MyController;
import com.mayi04.day20.mySpringmvc.annotation.MyRequestMapping;
import com.mayi04.day20.mySpringmvc.utils.ClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//1.定义前端控制器配置在web.xml中
//2.自定义Dispatcher,在init中初始化有注解的类到sping容器中spingmvcbeans = key 类名小写，value 类
//3.遍历所有的类 查看类上是否有映射的注解，将有映射注解的类保存到 urlBane 中 key url value 类 将方法也保存起来 key 为url value 方法名
public class MyDispatcherServlet extends HttpServlet {

    private ConcurrentHashMap<String,Object> springmvcbeans = new ConcurrentHashMap<String, Object>();
    private ConcurrentHashMap<String,Object> urlbeans = new ConcurrentHashMap<String, Object>();
    private ConcurrentHashMap<String,String> urlMethod = new ConcurrentHashMap<String, String>();


    @Override
    public void init() throws ServletException {
        //1.获取包下所有的类
        List<Class<?>> classes = ClassUtil.getClasses("com.mayi04.day20.mySpringmvc.controller");
        //2.将有注释的类放入到spingmvcbeans容器中
        try {
            initControllerAnnotation(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3将有映射注解的类和方法放入到容器中
        try {
            initMappingAnnotation();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //初始化urlBeans 和 urlMethod
    private void initMappingAnnotation() throws IllegalAccessException, InstantiationException {
        for(Map.Entry<String,Object> object : springmvcbeans.entrySet() ){
            initUrlAndMethod(object.getValue());
        }

    }

    private void initUrlAndMethod(Object object) throws InstantiationException, IllegalAccessException {
        Class<?> classInfo = object.getClass();
        MyRequestMapping myRequestMapping = classInfo.getAnnotation(MyRequestMapping.class);
        if(myRequestMapping!=null){
            //判断方法上是否有映射注解
            Method[] methods = classInfo.getDeclaredMethods();
            String url = "";
            for(Method method:methods){
                MyRequestMapping myRequestMapping1 = method.getDeclaredAnnotation(MyRequestMapping.class);
                if(myRequestMapping1!=null){
                    url = myRequestMapping.value()+myRequestMapping1.value();
                    urlbeans.put(url,ClassUtil.newInstance(classInfo));
                    urlMethod.put(url,method.getName());
                }
            }
        }
    }

    //将带有MyController注解的类放入到容器中
    private void initControllerAnnotation(List<Class<?>> classes) throws InstantiationException, IllegalAccessException {
        for(Class<?> classInfo : classes){
            MyController myController = classInfo.getAnnotation(MyController.class);
            if(myController!=null){
                String beanId = ClassUtil.toLowerCaseFirstOne(classInfo.getSimpleName());
                Object object = ClassUtil.newInstance(classInfo);
                if(object!=null){
                    springmvcbeans.put(beanId,object);
                }
            }
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Object object = urlbeans.get(url);
        String methodName = urlMethod.get(url);
        String pageResult = (String) methodInvoke(object,methodName);
        resp.getWriter().println(pageResult);

        resourceViewResolver(pageResult,req,resp);
    }

    private void resourceViewResolver(String pageResult, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix+pageResult+suffix).forward(req,resp);
    }


    public Object methodInvoke(Object object,String methodName){
       Class<?> classInfo = object.getClass();
       try {
           Method method = classInfo.getMethod(methodName);
           Object result = method.invoke(object);
           return  result;
       } catch (Exception e) {
           e.printStackTrace();
       }
        return null;
   }
}
