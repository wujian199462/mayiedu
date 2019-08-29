package com.mayi04.day21.teacher;

import java.sql.Connection;

//管理线程池
public class ConnectionPoolManager {
    private static DbBean dbBean = new DbBean();
    private static ConnectionPool connectionPool = new ConnectionPool(dbBean);

    //获取链接(重复利用机制)
    public static Connection getConnection(){
        return connectionPool.getConnection();
    }
    //是否链接 （可回收机制）
    public static void releaseConnection(Connection connection){
        connectionPool.releaseConnection(connection);
    }
}
