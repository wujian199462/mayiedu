package com.mayi04.day24.my;

/**
 * 手写linkedList
 * @param <E>
 */
public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private Node first;
    private Node last;

    @Override
    public void add(E e) {
        Node<E> node = new Node<>();
        node.e = e;
        if(size==0){
            first = node;
        }else {
            last.next = node;
            node.prev = last;
        }
        last = node;
        size++;
    }

    @Override
    public void add(int index, E e) {
        if(index == size){
            add(e);
            return;
        }
        checkIndex(index);
        Node<E> oldNode = getNode(index);
        Node<E> prevNode = oldNode.prev;
        Node<E> nextNode = oldNode.next;
        Node<E> newNode = new Node<>();
        newNode.e = e;
        if(prevNode==null){
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }else {
            newNode.prev = prevNode;
            prevNode.next = newNode;
            newNode.next = oldNode;
            oldNode.prev = newNode;
        }
        size++;
    }


    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> oldNode = getNode(index);
        Node<E> prevNode = oldNode.prev;
        Node<E> nextNode = oldNode.next;
        if(prevNode==null){
            first = nextNode;
            first.prev = null;
        }else {
            prevNode.next = nextNode;

        }
        if(nextNode==null){
            last = prevNode;
            last.next = null;
        }else {
            nextNode.prev = prevNode;
        }
        size--;
        return oldNode.e;
    }

    @Override
    public boolean remove(E e) {
        for(int i= 0;i<size;i++){
            E value = get(i);
            if(value.equals(e)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        Node node = getNode(index);
        return (E)node.e;
    }


    private Node getNode(int index){
        checkIndex(index);
        Node node = null;
        if(first!=null){
            node = first;
            for(int i=0;i<index;i++){
                node = node.next;
            }
        }
        return node;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void checkIndex(int index){
        if(index>=size||index<0)
            throw new IndexOutOfBoundsException("下标越界");
    }


    private class Node<E>{
        E e;
        Node<E> prev;
        Node<E> next;
    }


    public static void main(String[] args) {
        MyList<String> myList = new MyLinkedList();
        myList.add("a");
        myList.add("b");
        myList.add("c");
        myList.add(3,"dd");
        myList.remove(3);
        for(int i=0;i<myList.getSize();i++){
            System.out.println(myList.get(i));
        }
    }
}
