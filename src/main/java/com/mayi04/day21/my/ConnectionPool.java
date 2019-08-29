package com.mayi04.day21.my;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {
    //空闲的连接
    private static List<Connection> freeConnections = new Vector<Connection>();
    //活动的连接
    private static List<Connection> activeConnections = new Vector<Connection>();
    private static String driver ="";
    private static String url ="";
    private static String user ="";
    private static String password ="";
    private static int minConnections ;
    private static int maxConnections ;
    private static int initConnections ;
    private static int connTimeOut ;
    private static int maxActiveCOnnections ;
    private static int connectionTimeOut ;
    //记录连接数
    private static int conCount =0 ;

    private static Properties properties = new Properties();
    static {
        try {
            System.out.println("静态代码块只加载一次");
            InputStream inputStream = Object.class.getResourceAsStream("/jdbc.properties");
            properties.load(inputStream);
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
            password = properties.getProperty("jdbc.password");
            minConnections = Integer.parseInt(properties.getProperty("jdbc.minConnections"));
            maxConnections = Integer.parseInt(properties.getProperty("jdbc.maxConnections"));
            initConnections = Integer.parseInt(properties.getProperty("jdbc.initConnections"));
            inputStream.close();
            initConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public synchronized static Connection getConnection(){
        try {
            Connection connection = null;
            //小于最大连接数
            if (conCount < maxConnections) {
                if (freeConnections.size() > 0) {
                    connection = freeConnections.remove(0);
                } else {
                    connection = newConnection();
                }
                if (isAvailable(connection)) {
                    activeConnections.add(connection);
                } else {
                    conCount--;
                    getConnection();
                }
            } else {
                ConnectionPool.class.wait();
                getConnection();
            }
            return connection;
        }catch (Exception e){
            return null;
        }
    }

    public synchronized static void releaseConnection(Connection connection){
        try {
            if (isAvailable(connection)) {
                //如果
                if (freeConnections.size() < maxConnections) {
                    freeConnections.add(connection);
                } else {
                    connection.close();
                }
                activeConnections.remove(connection);
                conCount--;
                ConnectionPool.class.notifyAll();
            }
        }catch (Exception e){

        }
    }



    public static void initConnection(){
        //1初始化空闲池
        for(int i = 0;i <initConnections;i++ ){
            Connection connection = newConnection();
            if(isAvailable(connection)){
                freeConnections.add(connection);
            }else {
                conCount--;
                initConnection();
            }

        }
    }

    //判断连接是否有用
    public static boolean isAvailable(Connection connection){
        try {
            if (connection == null && connection.isClosed()) {
                return false;
            }
        }catch (Exception e){

        }
        return true;
    }


    public synchronized static Connection newConnection(){
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,user,password);
            conCount++;
            return connection;
        }catch (Exception e){
            return null;
        }
    }



    public static void main(String[] args) {
        String a = properties.getProperty("jdbc.user");
        String p = properties.getProperty("jdbc.password");
        System.out.println(a);
        System.out.println(p);
    }

}
