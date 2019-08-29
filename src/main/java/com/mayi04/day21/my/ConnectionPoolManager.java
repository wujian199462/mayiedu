package com.mayi04.day21.my;

import java.sql.Connection;

public class ConnectionPoolManager {



    //获取链接(重复利用机制)
    public static Connection getConnection(){
        return ConnectionPool.getConnection();
    }
    //是否链接 （可回收机制）
    public static void releaseConnection(Connection connection){
        ConnectionPool.releaseConnection(connection);
    }
}
