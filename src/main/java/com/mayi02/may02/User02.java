package com.mayi02.may02;

//懒汉式
public class User02 {

    private  static User02 user02;
    private  User02(){};
    public  static  synchronized   User02 getInstance(){
        if(user02 == null){
            user02 = new User02();
        }
        return user02;
    }

    public static void main(String[] args) {
        User02 user021 = User02.getInstance() ;
       User02 user022 = User02.getInstance();
        System.out.println(user021==user022);
    }
}
