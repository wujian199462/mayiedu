package com.mayi04.day22.teacher;

import com.mayi04.day22.teacher.orm.utils.JDBCUtils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
       /* String insertSql = "insert into user(userName,userAge) values(?,?)";
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("wujian");
        arrayList.add(20);
        int insert = JDBCUtils.insert(insertSql,false,arrayList);
        System.out.println("insert :"+insert);*/

       //查询
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("wujian");
        arrayList.add(20);
        ResultSet res = JDBCUtils.query("select *from user where userName =? and userAge = ?",arrayList);
        while(res.next()){
            String userName = res.getString("userName");
            System.out.println("userName:"+userName);
        }
    }
}
