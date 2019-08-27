package com.mayi04.day19.mySpringIOC_02.spring;


import com.mayi04.day19.mySpringIOC_02.annotation.MyResource;
import com.mayi04.day19.mySpringIOC_02.annotation.MyService;
import com.mayi04.day19.mySpringIOC_02.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyAnnotationClassPathXmlApplicationContext {

    //要扫的包路径
    private String packageName;
    //存取bean的容器
    private ConcurrentHashMap<String,Object> initBeanMap = null;

    public MyAnnotationClassPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        initBeanMap = new ConcurrentHashMap<String, Object>();
        initBean();
        initEntryField();
    }


    //初始化属性
    public void initEntryField() throws Exception {
        //1.遍历所有的bean 容器对象
        for (Map.Entry<String, Object> entry:initBeanMap.entrySet()){
            //2.判断属性上面是否有加注解
            Object bean = entry.getValue();
            attrAssign(bean);
        }
    }



    //依赖注入注解原理
    public void attrAssign(Object object) throws Exception {
        //1.使用反射机制，获取当前类的所有属性
        Class<? extends Object> classInfo = object.getClass();
        Field[] declaredFields= classInfo.getDeclaredFields();
        //2.判断当前类属性是否存在注解
        for(Field field: declaredFields){
            MyResource extResources = field.getAnnotation(MyResource.class);
            if(extResources != null){
                //获取属性名称
                String beanId = field.getName();
                Object bean =  getBean(beanId);
                if(bean != null){
                    //3.默认使用属性名称，查找bean容器对象 1参数当前对象，2参数给属性赋值
                    field.setAccessible(true);//运行访问私有属性
                    field.set(object,bean);
                }
            }
        }
        //3.默认使用属性名称，查找bean容器对象
    }


    public Object getBean(String beanId) throws Exception {
        if(beanId==null){
            throw new Exception("beanId参数不能为空");
        }
        return initBeanMap.get(beanId);
    }

    public ConcurrentHashMap<String,Object> initBean() throws IllegalAccessException, InstantiationException {
        List<Class<?>> classes = getClassByPkName();
        for(Class<?> classInfo : classes){
          MyService myService =  classInfo.getAnnotation(MyService.class);
          if(myService!=null){
              String beanId =toLowerCaseFirstOne(classInfo.getSimpleName());
              Object newInstance = classInfo.newInstance();
              initBeanMap.put(beanId,newInstance);
          }
        }
        return initBeanMap;
    }

    //获取首字母小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //根据报名获取所有的，类
    public List<Class<?>> getClassByPkName(){
        List<Class<?>> classes = new ArrayList<>();
        classes = ClassUtil.getClasses(packageName);
        return classes;
    }
}
