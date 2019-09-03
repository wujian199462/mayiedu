package com.mayi04.day23.my1;

public interface My1List<E> {
    public void add(E e);
    public void add(int index, E e);
    public E remove(int index);
    public boolean remove(E e);
    public E get(int index);
    public int getSize();
}
