package com.lzp.arouter.mine;

/**
 * @author Li Xiaopeng
 * @date 2019/3/15
 */
public class Node {
   // private Node pre;
    private Node next;
    private Node item;
    private int id;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }
*/
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getItem() {
        return item;
    }

    public void setItem(Node item) {
        this.item = item;
    }
}
