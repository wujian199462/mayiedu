package com.mayi04.day23.teacher;

import java.util.Arrays;

/**
 * 自定义的ArrayList集合
 */
public class ExtArrayList<E> implements ExtList<E> {
    //ArrayList底层采用数组存放
    private Object[] elementData;
    //默认数组容量
    private static final int DEFAULT_CAPACITY = 10;
    //记录实际ArrayList大小
    private int size;

    //ArrarList 数组初始的容量
    public ExtArrayList(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException("初始容量不能小于0");
        }
        elementData = new Object[initialCapacity];
    }

    //默认初始化容量为10
    public ExtArrayList() {
        this(DEFAULT_CAPACITY);
    }

    //线程安全问 每次扩容都是1.5倍
    public void add(E e){
        //1.判断实际存放的数据容量是否大于elementData
        ensureExplicitCapacity(size+1);
        //2.使用下表进行赋值
        elementData[size++] = e;
    }

    public void add(int index,E e){
        //1.判断实际存放的数据容量是否大于elementData
        ensureExplicitCapacity(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size - index);
        elementData[index] = e;
        size ++;
    }


    //数组扩容  minCapacity 最当前size +1
    private void ensureExplicitCapacity(int minCapacity){ //最小容量
        if(size == elementData.length){
            //原来本身elementData容量大小
            int oldCapacity = elementData.length;
            //新数据容量大小  (oldCapacity >> 1) = oldCapacity/2
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            //如果初始化容量为1的时候，那么他扩容的大小为多少呢？
            if(newCapacity - minCapacity <0)
                newCapacity = minCapacity;
            //将老数组赋值到新数组中去
            elementData = Arrays.copyOf(elementData,newCapacity); //扩容 newCapacity扩容后大小
        }
    }



    //使用下表获取数组元素
    public E get(int index){
        rangeCheck(index);
        return elementData(index);
    }
    E elementData(int index){
        return (E)elementData[index];
    }

    public E remove(int index){
        rangeCheck(index);
        //1.使用下标查询该值是否存在
        E e = get(index);
        //计算删除元素后面的长度
        int numMoved = size - index -1;
        //2.删除原理分析  使用arraycopy往前移动数据，将最后一个置为空
        if(numMoved > 0)
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        elementData[--size] = null;//将最后一个元素置为null
        return e;
    }

    //删除相同元素删除第一个
    public boolean remove(E e){
        for (int i = 0; i <elementData.length ; i++) {
            E value = elementData(i);
            if(value.equals(e)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    private void rangeCheck(int index){
        if(index >= size)
            throw new IndexOutOfBoundsException("越界了");
    }

    public int getSize(){
        return size;
    }
}
