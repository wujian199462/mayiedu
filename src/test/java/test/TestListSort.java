package test;

import java.util.ArrayList;
import java.util.List;

public class TestListSort {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>(1);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(1,"5");
        //System.out.println(list.toString());
        list.size();
        list.add(5,"dasd");
        System.out.println(list.toString());
    }


}
