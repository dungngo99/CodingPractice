package LinkedList;

import java.util.StringJoiner;

public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int length;

    public void append(int num) {
        Node node = new Node(num);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }

        this.tail.next = node;
        this.tail = node;
        this.length++;
    }

    public Node find(int num) {
        if (this.head == null)
            return null;

        Node tmp = this.head;
        while (tmp != null && tmp.data != num)
            tmp = tmp.next;

        return tmp;
    }

    public void insert(int num, int index) {
        if (index >= this.length || index < 0)
            return;

        Node tmp = this.head;
        Node prev = null;

        for (int i = 0; i < index; i++) {
            prev = tmp;
            tmp = tmp.next;
        }

        Node newNode = new Node(num);
        prev.next = newNode;
        newNode.next = tmp;
    }

    public void print() {
        StringJoiner joiner = new StringJoiner("->");
        Node tmp = this.head;
        while (tmp != null) {
            joiner.add(String.valueOf(tmp.data));
            tmp = tmp.next;
        }
        System.out.println(joiner.toString());
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 1; i < 5; i++)
            list.append(i);
        System.out.println(list);

        System.out.println(list.find(4));
        System.out.println(list.find(-1));
        System.out.println(list.find(6));
    }
}
