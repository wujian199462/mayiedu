package com.mayi04.day21.my;

import java.sql.Connection;

public class MyTest {
    public static void main(String[] args) {
        ThreadConnection threadConnection =  new ThreadConnection();
        for(int i=0;i<3;i++){
            Thread thread = new Thread(threadConnection,"线程="+i);
            thread.start();
        }
    }
}
class ThreadConnection implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            Connection connection =  ConnectionPoolManager.getConnection();
            System.out.println(Thread.currentThread().getName()+","+connection);
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
}