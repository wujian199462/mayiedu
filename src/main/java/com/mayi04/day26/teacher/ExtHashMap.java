package com.mayi04.day26.teacher;

public class ExtHashMap<K,V> implements ExtMap<K,V> {
    //定义table 存放HashMap 数组元素,默认时没有初始化容器，懒加载
    Node<K,V>[] table = null;
    //2.实际用到table 存储容量大小
    int size;
    //3.负载因子0.75扩容的时候才会用到 负载因子越小，hash冲突越小
    float DEFAULT_LOAD_FACTOR = 0.75f;
    //4.table 默认初始大小 16
    static final int DEFAULT_INITIAL_CAPACITY = 1<<4; //16

    @Override
    public V put(K key, V value) {
        //1.判断table 数组大小是否为空（如果为空的情况下，做初始化操作）
        if(table == null){
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //2.判断数组 是否需要扩容 为什么要扩容？扩容之后有什么影响。什么时候开始扩容的
          //实际存储大小 = 负载因子* 初始容量 = 0.75*16 = 12
          //如果size>12 的时候就需要开始扩容数组，扩容数组大小之前的两倍
        if(size>DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR){
            //需要开始对table进行数组扩容
            resize();
        }
        //3.计算hash值指定下标位置
        int index = getIndex(key,DEFAULT_INITIAL_CAPACITY);
        Node<K,V> node = table[index];
        if(node == null){
            //没有发生hash冲突
            node = new Node<K,V>(key,value,null);
            size++;
        }else {
            Node<K, V> newNode = node;
            while (newNode != null) {
                //已经发生hash冲突 直接添加到前面
                if (newNode.getKey().equals(key) || newNode.getKey() == key) {
                    //hashCode相同，而且equals相等 说明是同一个对象 修改值
                    // node.value = value;
                    return newNode.setValue(value);
                } else {
                    //继续添加排在前面。hashCode取模的余数index相同对象不同
                    if(newNode.next == null) { //说明遍历到最后一个node ,添加node
                        node = new Node<K, V>(key, value, node);
                        size++;
                    }
                }
                newNode = newNode.next;
            }
        }
        table[index] =node;
        return null;
    }

    //对table进行扩容
    private void resize() {
        //1.生产新的table是之前的两倍大小new
        Node<K,V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 1];
        //2.重新计算index索引位置
        for(int i = 0;i<table.length;i++){
            //存放之前的table 原理的node
            Node<K,V> oldNode = table[i];
            while(oldNode != null){
                K oldK = oldNode.key;
                //重新计算index
                int index = getIndex(oldK,newTable.length);
                //存放在之前的table 原来的 node next
                Node<K,V> oldNext = oldNode.next;
                //如果index 下标在新newTble
                oldNode.next = newTable[index];
                //将之前的node赋值给newTable[index]
                newTable[index] = oldNext;
                //继续是否继续循环遍历
                oldNode = oldNext;
            }
        }
        //3.将newtable 赋值给老table
    }

    //测试方法，打印所有的链表元素
    void print(){
        for(int i = 0;i<table.length;i++){
            Node<K,V> node = table[i];
            System.out.print("下标位置["+i+"]");
            while(node!=null){
                System.out.print("[key:"+node.getKey()+",value:"+node.getValue()+"]");
                node = node.next;
//                if(node.next!=null){
//                    node = node.next;
//                }else {
//                    //结束循环
//                    node = null;
//                }
            }
            System.out.println();
        }
    }

    public int getIndex(K k,int length){
        int hashCode = k.hashCode();
        int index = hashCode%length;
        return index;
    }

    @Override
    public V get(K k) {
        //1.使用取模算法定位数组链表
        Node<K,V> node = getNode(table[getIndex(k,DEFAULT_INITIAL_CAPACITY)],k);
        return node == null?null:node.value;
    }


    public Node<K,V> getNode(Node<K,V> node,K k){
        while(node != null){
            if(node.getKey().equals(k)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    //定义节点
    class Node<K,V> implements Entry<K,V>{

        //存放Map 集合key
        private K key;
        //存放Map 集合value
        private V value;
        //下一个节点
        private Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            //设置新值的时候返回老的值
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
