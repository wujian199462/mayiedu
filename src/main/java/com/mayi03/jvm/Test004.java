package com.mayi03.jvm;

import java.util.ArrayList;
import java.util.List;

public class Test004 {

    //内存溢出问题
    // -Xms1m -Xmx10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
    //堆初始值1M 最大值10M
    public static void main(String[] args) {
        List<Object> listObject = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            Byte[] bytes = new Byte[1 * 1024 * 1024];
            listObject.add(bytes);
        }
        System.out.println("添加成功");
    }
}
