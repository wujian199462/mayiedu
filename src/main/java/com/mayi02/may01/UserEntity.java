package com.mayi02.may01;

public class UserEntity {
    private String userName;

    public UserEntity(){
        System.out.println("对象初始化。。。");
       // throw new RuntimeException();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
     //   UserEntity userEntity = new UserEntity();
        //userEntity.userName = "蚂蚁课堂";
       // System.out.println(userEntity.userName);
        //2.使用Java反射鸡翅创建对象
        Class<?> forName =  Class.forName("mayi02.may01.UserEntity");
        UserEntity userEntity2 = (UserEntity) forName.newInstance();
        userEntity2.userName = "发射名字";
        System.out.println(userEntity2.userName);
    }
}
