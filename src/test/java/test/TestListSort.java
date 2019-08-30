package test;

import java.util.ArrayList;
import java.util.List;

public class TestListSort {


    public static void main(String[] args) {
        List<String> alist = new ArrayList<String>();
        alist.add("a");
        alist.add("c");
        alist.add("b");
        List<String> blist = new ArrayList<String>();
        blist.add("e");
        blist.add("f");
        blist.add("d");

        alist.addAll(blist);
        System.out.println(alist.toString());
        blist = alist;
        System.out.println(blist.toString());
    }

}
