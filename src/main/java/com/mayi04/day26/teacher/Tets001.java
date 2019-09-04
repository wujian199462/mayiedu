package com.mayi04.day26.teacher;



public class Tets001 {
    public static void main(String[] args) {
        ExtHashMap extMap= new ExtHashMap<String ,String>();
        extMap.put("1号","aaaa");
        extMap.put("2号","bbbb");
        extMap.put("3号","aaaa");
        extMap.put("4号","bbbb");
        extMap.put("5号","aaaa");
        extMap.put("6号","bbbb");
        extMap.put("7号","bbbb");
        extMap.put("14号","aaaa");

        System.out.println(extMap.get("14号"));

    }
}
