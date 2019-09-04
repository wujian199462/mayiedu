package com.mayi04.day24.teacher;

public class ExtLinkedList<E> {

    //链表实际存储元素
    private int size;
    //第一个元素（头节点，为查询开始）
    private Node first;
    //最后一个元素（尾节点，为添加开始）
    private Node last;

    public void add(E e){
        //创建节点
        Node  node = new Node();
        //给节点赋值
        node.object = e;
        if(first == null){
            //添加第一个元素
            //给第一个元素赋值node节点赋值
            first = node;
        }else {
            //添加第二个或以上数据
            node.pre = last;
            //将上一个元素的next赋值
            last.next = node;
        }
        last = node;
        //实际长度++
        size++;
    }

    public void add(int index,E e){
        if(index==size){
            add(e);
            return;
        }
        checkElementIndex(index);
        ExtLinkedList<E>.Node oldNode = getNode(index);
        if(oldNode!=null){
            ExtLinkedList<E>.Node oldPrevNode = oldNode.pre;
            Node newNode = new Node();
            newNode.object = e;
            newNode.next = oldNode;
            if(oldPrevNode == null){
                first = newNode;
            }else {
                newNode.pre = oldPrevNode;
                oldPrevNode.next = newNode;
            }
            oldNode.pre = newNode;
            size++;
        }
    }

    public Object get(int index){
        Node node = getNode(index);
        return node.object;
    }

    private void checkElementIndex(int index) {
        if(!isElementIndex(index))
            throw new IndexOutOfBoundsException("查询越界");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index <size;
    }

    private void remove(int index){
        ExtLinkedList<E>.Node oldNode = getNode(index);
        if(oldNode!=null){
            //下一个节点
            ExtLinkedList<E>.Node oldNextNode = oldNode.next;
            //上一个节点
            ExtLinkedList<E>.Node oldPrevNode = oldNode.pre;
            if(oldPrevNode!=null) {
                oldPrevNode.next = oldNextNode;
                oldNode.pre = null;
            }else {
                first = oldNextNode;
            }
            if(oldNextNode!=null) {
                oldNextNode.pre = oldPrevNode;
                oldNextNode.next = null;
            }else {
                last = oldPrevNode;
            }
            oldNode.object = null;
            size--;
        }
    }


    public Node getNode(int index){
        checkElementIndex(index);
        Node node = null;
        if(first!=null){
            node = first;
            for(int i =0;i<index;i++){
                node = node.next;
            }
        }
        return node;
    }

    public int getSize(){
        return size;
    }


    //链表节点存储元素
    private class Node{
        //存放元素
         Object object;
        //上个节点
         Node pre;
        //下个节点
         Node next;
    }

    public static void main(String[] args) {
        ExtLinkedList<String> extLinkedList = new ExtLinkedList<String>();
        extLinkedList.add("a");
        extLinkedList.add("b");
        extLinkedList.add("c");
        extLinkedList.add("e");
        extLinkedList.add(-2,"dd");


       for(int i=0;i<extLinkedList.getSize();i++){
           System.out.println(extLinkedList.get(i));
       }


    }
}
