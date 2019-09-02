package com.mayi04.day22.my.util;

import java.util.ArrayList;
import java.util.List;

public class Myutils {

    //获取sql里面的参数
    public static List<String> getParams(String oldSql){
        List<String> paramsList = new ArrayList<String>();
        char[] sqlChar = oldSql.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<sqlChar.length;i++){
            if("#".equals(String.valueOf(sqlChar[i]))&&"{".equals(String.valueOf(sqlChar[i+1]))){
                flag = true;
            }
            if("}".equals(String.valueOf(sqlChar[i]))){
                flag = false;
                paramsList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
            if(flag&&!"#".equals(String.valueOf(sqlChar[i]))&&!"{".equals(String.valueOf(sqlChar[i]))){
                stringBuilder.append(sqlChar[i]);
            }
        }
        return paramsList;
    }


    //替换oldSql为newSql
    public static String getNewSql(String oldSql,List<String> paramsList){
        for(int i=0;i<paramsList.size();i++){
            String param = "#\\{"+paramsList.get(i)+"}";
            oldSql =myReplaceAll(oldSql,param,"?");
        }
        return oldSql;
    }

    public static String myReplaceAll(String sql, String oldString, String newString){
        String s1 = sql.replaceAll(oldString,newString);
        return s1;
    }
}
