package org.example.LinkedList;

import java.util.StringJoiner;

public class DoublyLinkedList {
    Node head;
    Node tail;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addNode(int num) {
        Node node = new Node(num);

        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }

        this.tail.next = node;
        node.prev = this.tail;
        this.tail = node;
    }

    public Node removeNode(int d) {
        if (this.head == null)
            return null;

        Node tmp = this.head;
        while (tmp != null && tmp.data != d)
            tmp = tmp.next;

        if (tmp == null)
            return null;

        Node prevNode = tmp.prev;
        Node nextNode = tmp.next;
        unLinkNode(tmp);

        if (prevNode != null)
            prevNode.next = nextNode;
        else
            this.head = nextNode;

        if (nextNode != null)
            nextNode.prev = prevNode;
        else
            this.tail = prevNode;

        return tmp;
    }

    public void unLinkNode(Node node) {
        node.next = null;
        node.prev = null;
    }

    public void updateNode(int oldD, int newD) {
        Node tmp = this.head;

        while (tmp != null && tmp.data != oldD)
            tmp = tmp.next;

        if (tmp != null)
            tmp.data = newD;
    }

    public String readNodes() {
        Node tmp = this.head;
        StringJoiner joiner = new StringJoiner("=");

        while (tmp != null) {
            joiner.add(String.valueOf(tmp.data));
            tmp = tmp.next;
        }
        return joiner.toString();
    }

    public static void main(String args[]) {
        int[] nums = new int[] { 5, 2, 1, 10, 8 };
        DoublyLinkedList list = new DoublyLinkedList();

        for (int num : nums) {
            list.addNode(num);
        }
        System.out.println(list.readNodes());

        list.updateNode(11, 7);
        list.updateNode(10, 6);
        list.updateNode(8, 12);
        System.out.println(list.readNodes());

        list.removeNode(5);
        list.removeNode(12);
        list.removeNode(13);
        list.removeNode(1);
        System.out.println(list.readNodes());
    }
}
