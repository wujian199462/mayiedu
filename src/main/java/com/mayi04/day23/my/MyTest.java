package com.mayi04.day23.my;

public class MyTest {
    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<String>(2);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(3,"a");
        for(int i =0 ;i<list.getSize();i++){
            System.out.println(list.get(i));
        }
    }
}
