package com.mayi04.day23.my;

public class ArrayList<E> implements MyList<E> {

    private Object[] elementDate;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity){
        if(initialCapacity<0)
            throw new IllegalArgumentException("容量不能小于0");
        elementDate = new Object[initialCapacity];
    }


    @Override
    public void add(E e) {

    }

    @Override
    public void add(int index, E e) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E remove(E e) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
