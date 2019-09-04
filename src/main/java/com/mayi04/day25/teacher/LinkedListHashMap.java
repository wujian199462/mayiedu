package com.mayi04.day25.teacher;

import java.util.LinkedList;

/**
 * 基于LinkedList实现hashMap
 * //1.jdk1.7时候，hashmap 使用数组+链表方式实现
 */
public class LinkedListHashMap {
    //Map 存放Entry对象信息
    LinkedList<Entry>[] tables = new LinkedList[998];

    public void put(Object key,Object value){
        Entry newEntry = new Entry(key, value);
        int hashCode = key.hashCode();
        //hash取模，获取余数
        int hash = hashCode%tables.length;
        //获取该下标元素，是否有LinkedList
        LinkedList<Entry> entryLinkedList = tables[hash];
        if(entryLinkedList == null){
            //美哟hash冲突
            entryLinkedList = new LinkedList<Entry>();
            entryLinkedList.add(newEntry);
            tables[hash] = entryLinkedList;
        }else {
            for(Entry entry:entryLinkedList){
                if(entry.key.equals(key)){
                    //equaks相等，hashCOde 一定相同说明时同一个对象
                    entry.value = value;
                }else {
                    //hashCode 相同，对象的值不一定相同
                    //发生hash冲突问题,直接在后面继续添加链表节点
                    entryLinkedList.add(newEntry);
                }
            }
        }

        //两个对象做比价的时候如果hashCode相同，对象的值是否一定相同，——不一定相同
        //两个对象做比较的时候如果equest比较相同，对象值是否一定相同——一定相同
    }

    public Object get(Object key){
        int hashCode = key.hashCode();
        //hash取模，获取余数
        int hash = hashCode%tables.length;
        LinkedList<Entry> linkedList =  tables[hash];
        for(Entry entry : linkedList){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return tables[hash];
    }

    public static void main(String[] args) {
        LinkedListHashMap linkedListHashMap = new LinkedListHashMap();
        linkedListHashMap.put("a","aaaa");
        linkedListHashMap.put("a","cccc");
        System.out.println(linkedListHashMap.get("a"));
    }

}

class Entry<Key,Value>{
    Key key;
    Value value;

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
