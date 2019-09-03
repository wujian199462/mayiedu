package com.mayi04.day23.my;

import net.sf.ehcache.ElementData;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {

    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity){
        if(initialCapacity<0)
            throw new IllegalArgumentException("容量不能小于0");
        elementData = new Object[initialCapacity];
    }


    @Override
    public void add(E e) {
        //当前次存的元素大小时size++
        ensuerExplicitCapacity(size+1);
        elementData[size++] = e;
    }

    @Override
    public void add(int index, E e) {
        if(index>size){
            throw new IndexOutOfBoundsException("添加的下标过界");
        }
        ensuerExplicitCapacity(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = e;
        size++;
    }



    @Override
    public E remove(int index) {
        rangeCheck(index);
        E e = get(index);
        int numMoved = size - index -1;
        if(numMoved>0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved) ;
        }
        elementData[--size] = null;
        return e;
    }

    @Override
    public boolean remove(E e) {
        for(int i =0 ;i<elementData.length;i++){
            E value = elementData(i);
            if(e.equals(value)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }


    @Override
    public int getSize() {
        return size;
    }

    public E elementData(int index){
        return (E) elementData[index];
    }

    /**
     *
     * @param minCapacity 当前操作数组必须的最小元素
     */
    private void ensuerExplicitCapacity(int minCapacity){
        if(size==elementData.length){ //需要扩容
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if(newCapacity - minCapacity <0 ){//特殊情况的时候如果时1时候
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData,newCapacity);
        }
    }

    private void rangeCheck(int index){
        if(index>=size)
            throw new IndexOutOfBoundsException("越界了");
    }
}
