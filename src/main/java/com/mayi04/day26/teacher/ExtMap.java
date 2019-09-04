package com.mayi04.day26.teacher;

/**
 * 纯手写map接口
 */
public interface ExtMap<K,V> {

    //向集合中插入数据
    public V put(K k,V v);

    //根据k从Map集合中查询元素
    public V get(K k);

    //获取集合元素个数
    public int size();

    interface Entry<K,V>{
        K getKey();

        V getValue();

        V setValue(V value);
    }
}


