package com.mayi04.day21.teacher;

import java.sql.Connection;

//链接数据库池
public interface IConnectionPool {
    //获取链接(重复利用机制)
    public  Connection getConnection();
    //是否链接 （可回收机制）
    public  void releaseConnection(Connection connection);
}
