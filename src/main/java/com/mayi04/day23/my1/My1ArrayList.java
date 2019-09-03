package com.mayi04.day23.my1;

import java.util.Arrays;

public class My1ArrayList<E> implements My1List<E> {

    private Object[] elementData;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public My1ArrayList() {
        //不传大小就默认大小为10
        this(DEFAULT_SIZE);
    }

    public My1ArrayList(int initSize){
        //传入参数的时候
        elementData = new Object[initSize];
    }

    @Override
    public void add(E e) {
        //1.判断是否需要扩容
        enSuerGrowth(size+1); //当你新存入一个元素的时候当前的大小就是size +1
        elementData[size++] = e;
    }

    private void enSuerGrowth(int minSize) {
        if(size == elementData.length){
            int oldSeize = elementData.length;
            int newSize = oldSeize + (oldSeize >> 1);
            if(newSize - minSize<0){
                newSize = minSize;
            }
            //开始扩容
            elementData = Arrays.copyOf(elementData,newSize);
        }
    }

    @Override
    public void add(int index, E e) {
        if(index>size){
            throw new IndexOutOfBoundsException("新加元素下标过大");
        }
        enSuerGrowth(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = e;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E e = elementData(index);
        int numMove = size - index -1;
        if(numMove>0){
            System.arraycopy(elementData,index+1,elementData,index,size-index-1);
        }
        elementData[size--]=null;
        return e;
    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public boolean remove(E e) {
        for(int i=0;i<elementData.length;i++){
            E value = elementData(i);
            if(value.equals(e)){
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

    public void rangeCheck(int index){
        if(index>=size)
            throw new IndexOutOfBoundsException("数组越界");
    }
}
