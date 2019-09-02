package com.mayi04.day22.my.util;

import com.mayi04.day21.my.ConnectionPool;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCUtils {

    private static String connect;
    private static String driverClassName;
    private static String URL;
    private static String username;
    private static String password;
    private static boolean autoCommit;

    /** 声明一个 Connection类型的静态属性，用来缓存一个已经存在的连接对象 */
    private static Connection conn;

    static {
        config();
    }

    /**
     * 开头配置自己的数据库信息
     */
    private static void config() {
        /*
         * 获取驱动
         */
        driverClassName = "com.mysql.cj.jdbc.Driver";
        /*
         * 获取URL
         */
        URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        /*
         * 获取用户名
         */
        username = "root";
        /*
         * 获取密码
         */
        password = "123";
        /*
         * 设置是否自动提交，一般为false不用改
         */
        autoCommit = false;

    }

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        Connection connection = DriverManager.getConnection(URL,username,password);
        return connection;
    }


    public static int insert(String sql, List<Object> list) throws SQLException, ClassNotFoundException {
       Connection conn =  getConn();
       PreparedStatement ps =  conn.prepareStatement(sql);
        for(int i=0;i<list.size();i++){
            ps.setObject(i+1,list.get(i)); //下标从1开始
        }
        ps.execute();
        return 1;
    }





}
