package com.mayi04.day21.teacher;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

/** 数据库链接池的原理
 * 1.空闲线程 容器，没有被使用的连接存放
 * 2.活动线程 容器 正在使用的链接
 * #####核心步骤####
 * 2.1 初始化线程池（初始化空闲线程）
 * 3.调用getConnection方法 获取链接
 *   3.1先去 freeConnection 获取当前链接，存放在activeConnection活动集合中去
 * 4.调用releaseConnection方法 释放链接
 *   4.1 获取 activeConnection集合链接，转移到 freeConnection集合中
 *
 */
public class Test001  {
    //使用线程安全的集合 空闲线程容器 没有被使用的连接存放
    private List<Connection> freeConnection = new Vector<Connection>();
    //使用线程安全的集合 活动线程容器 容器正在使用的链接
    private List<Connection> activeConnection = new Vector<Connection>();

    public static void main(String[] args) {
        ThreadConnection threadConnection =  new ThreadConnection();
        for(int i=0;i<3;i++){
            Thread thread = new Thread(threadConnection,"线程"+i);
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