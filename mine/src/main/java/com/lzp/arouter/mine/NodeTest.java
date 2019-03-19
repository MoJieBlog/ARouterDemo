package com.lzp.arouter.mine;

/**
 * @author Li Xiaopeng
 * @date 2019/3/15
 */
public class NodeTest {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);

        //相交部分
        node4.setNext(node5);
        node5.setNext(node6);

        //第一个链表1-2-3-4-5-6
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);


        //第二个链表 7-8-9-10-11-4-5-6
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node10);
        node10.setNext(node11);
        node11.setNext(node4);


    }
}
