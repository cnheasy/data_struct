package com.cnheasy.link;

/**
 * 链表
 */
public class LinkList<E> {


    /**
     * 节点.作为一个内部类.外部调用这不需要知道实现
     */
    private class Node {
        public E e;//数据
        public Node next;//指向下一个节点

        /**
         * 定义构造函数
         *
         * @param e
         * @param next
         */
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
//           this.e=e;
//           this.next=null;
            //这种写法可以替换成
            this(e, null);//这里this调用的构造函数只能为第一个
        }

        public Node() {
//           this.e=null;
//           this.next=null;
            //替换
            this(null, null);//这里this调用的构造函数只能为第一个
        }

        /**
         * 方便调试,重写toString
         *
         * @return
         */
        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;//定义个头节点  ,,也称为虚拟头节点,(指向的元素都为空)
    private int size;//链表的长度 默认为0

    /**
     * 定义构造
     *
     * @param node 节点
     * @param size 长度
     */
    public LinkList(Node node, int size) {
        this.head = new Node(null, null);//创建头节点为空
        this.size = 0;
    }

    public LinkList() {
        this.head = new Node(null, null);//默认的节点为空
        this.size = 0;//默认长度为0
    }

    /**
     * 获取链表的长度
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 向链表中指定位置添加元素
     * 思想:定义一个新的变量,让这个变量等于头节点,遍历链表直到index-1的位置;
     * 让index-1位置的节点的下一个节点指向新的节点,新的节点的下一个指向index节点;
     *
     * @param index 位置
     * @param e     元素
     */
    public void add(int index, E e) {
        //判断位置是否合法,不合法,抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed,Illegal index");
        }
        //创建一个
        Node prev = head;
        for (int x = 0; x < index; x++) {
            prev = prev.next;
        }
//            Node node = new Node(e);
//            node.next=prev.next;
//            prev.next=node;
        //优化写法
        prev.next = new Node(e, prev.next);
        size++;

    }

    /**
     * 头节点添加
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向链表末尾添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取链表中的指定位置的元素
     *
     * @param index
     * @return 获取的元素
     */
    public E get(int index) {
        //判断位置是否合法,不合法,抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed,Illegal index");
        }
        Node currentNode = head.next;//从索引为0的位置开始遍历

        for (int x = 0; x < size; x++) {
            currentNode = currentNode.next;
        }
        return currentNode.e;
    }

    /**
     * 获取第一个元素
     *
     * @return 获取的元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表末尾元素¬
     *
     * @return 获取的元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 删除指定位置的元素
     * 思想:从头节点开始寻找,直到index的前一个元素,  让这个元素直接指向index的后一个元素即可
     *
     * @param index 位置
     * @return 删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed,Illegal index");
        }
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        return delNode.e;
    }

    /**
     * 移除第一个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 移除最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 更新指定节点的元素
     *
     * @param index 位置
     * @param e     元素
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed,Illegal index");
        }
        Node currtentNode = head.next;

        for (int i = 0; i < index; i++) {
            currtentNode = currtentNode.next;
        }
        currtentNode.e = e;
    }

    /**
     * 包含某个元素
     *
     * @param e 元素
     * @return
     */
    public boolean contains(E e) {
        Node currtentNode = head.next;

        while (currtentNode != null) {
            if (currtentNode.e.equals(e)) {
                return true;
            }
            currtentNode = currtentNode.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node currtentNode = head.next;
        //while 方式写法
        while (currtentNode != null) {
            res.append(currtentNode + "->");
            currtentNode = currtentNode.next;
        }
        //for写法
//        for (Node currentNode = head.next;currentNode!=null;currentNode=currtentNode.next){
//            res.append(currtentNode + "->");
//        }

        res.append("NULL");

        return res.toString();
    }
}
