package com.mayi04.day24.my;

public interface MyList<E> {
    public void add(E e);
    public void add(int index, E e);
    public E remove(int index);
    public boolean remove(E e);
    public E get(int index);
    public int getSize();
}
