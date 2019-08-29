package com.mayi04.day21.teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

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
public class ConnectionPool implements IConnectionPool {
    //使用线程安全的集合 空闲线程容器 没有被使用的连接存放
    private List<Connection> freeConnection = new Vector<Connection>();
    //使用线程安全的集合 活动线程容器 容器正在使用的链接
    private List<Connection> activeConnection = new Vector<Connection>();
    private DbBean dbBean;
    int countConne = 0;

    public ConnectionPool(DbBean dbBean) {
        //获取配置文件
        this.dbBean = dbBean;
        init(dbBean);
    }


    //初始化线程池（初始化空闲线程）
    private void init(DbBean dbBean)  {
        if(dbBean == null){
            return;// 最好抛出异常
        }
        //1.获取初始化链接数
        for(int i = 0;i<dbBean.getInitConnections();i++){
            Connection newConnection = newConnection();
            if(newConnection!=null){
                //添加到空闲线程
                freeConnection.add(newConnection);
            }
        }
        //2.创建Connection链接
        //3.放在freeConnection集合
    }

    private  Connection newConnection(){
        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(),dbBean.getUserName(),dbBean.getPassword());
            countConne++;
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized Connection getConnection() {
        try {
            Connection connection = null;
            if (countConne < dbBean.getMaxActiveConnections()) {
                //小于最大活动链接数
                //1.判断空闲线程是否有数据
                if (freeConnection.size() > 0) {
                    //空闲线程存在链接
                    //拿到在删除
                    connection = freeConnection.remove(0);
                } else {
                    //创建新的链接
                    connection = newConnection();
                }
                //判断链接是否可用
                boolean available = isAvailable(connection);
                if (available) {
                    //往活动线程里面存
                    activeConnection.add(connection);
                } else {
                    //怎么使用重试 递归算法
                    countConne--;
                    connection = getConnection();
                }
            } else {
                //大于最大活动链接数，进行等待
                wait(dbBean.getConnTimeOut());
                connection = getConnection();
            }
            return connection;
        }catch (Exception e){
            return null;
        }
    }

    //判断链接是否可用
    public boolean isAvailable(Connection connection){
        try {
            if(connection == null||connection.isClosed()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public synchronized void  releaseConnection(Connection connection) {
        try {
            //判断你的链接是否可用
            if (isAvailable(connection)) {
                //判断空闲线程是否大于活动线程，我是怎么知道空闲线程是否已经满了
                if (freeConnection.size() < dbBean.getMaxConnections()) {
                    //空闲线程没有满
                    freeConnection.add(connection);//回收连接
                } else {
                    //空闲线程满
                    connection.close();
                }
                activeConnection.remove(connection);
                countConne--;
                notifyAll();
            }
        }catch (Exception e){

        }
            //判断空闲线程是否大于活动线程
    }
}
