package com.itmayi04.day19.mySpringIOC.spring;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class MyClassPathXmlApplicationContext {
    private String xmlPath;

    public MyClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId) throws Exception {
        //1.遍历xml的配置文件
            List<Element> elements = getElements();
        //2.获取对应的beanId的className
         String className = getClassName(elements,beanId);
        //3.根据class 反射生产类返回
        return newInstance(className);
    }


    public Object newInstance(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> clazz =   Class.forName(className);
        return clazz.newInstance();
    }


    //获取对应的beanId的className
    public String getClassName(List<Element> elements,String beanId){
        String className = null;
        for (Element element :elements){
            //2.获取配置<bean> id 根据他的class 获取对象class 地址
            String xmlBeanId =  element.attributeValue("id"); //获取每个节点的id
            if(xmlBeanId==null|| StringUtils.isEmpty(xmlBeanId)){
                continue;
            }
            if(xmlBeanId.equals(beanId)){
                 className = element.attributeValue("class");
            }
        }
        return className;
    }


    //解析xml
    public List<Element> getElements() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(getResourcesAsStream());
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        return elements;
    }


    //获取根目录下面的文件的配置文件的流
    public InputStream getResourcesAsStream(){
        return this.getClass().getClassLoader().getResourceAsStream(xmlPath);
    }

}
