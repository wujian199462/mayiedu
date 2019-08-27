package com.mayi02.may02;

//枚举单例
public class User04 {
    private User04(){};
    public static User04 getInstance(){
        return SingletonUserEnum.INSTANCE.getInstance();
    }
    //本身枚举就是单例
    static enum SingletonUserEnum {
        INSTANCE;
        private User04 user04;
        private SingletonUserEnum(){
            user04 = new User04();
        }
        public User04 getInstance(){
            return this.user04;
        }
    }
    public static void main(String[] args) {
        User04 u1 = User04.getInstance();
        User04 u2 = User04.getInstance();
        System.out.println(u1 == u2);
    }
}
