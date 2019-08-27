package com.mayi02.may02;

//双重检验锁
public class User05 {
    private static User05 user05;
    private User05 (){};
    //线程安全 效率低
    public static User05 getInstance(){
        if(user05 == null){
            synchronized (User05.class){
                if(user05 == null){
                    user05 = new User05();
                }
            }
        }
        return user05;
    }

    public static void main(String[] args) {
        User05 u1 = User05.getInstance();
        User05 u2 = User05.getInstance();
        System.out.println(u1 == u2);
    }
}
