package LinkedList;

import java.util.HashSet;
import java.util.Set;

class RemoveDuplicates {
    public static void solution(LinkedList list) {
        Set<Integer> set = new HashSet<>();

        Node cur = list.head;
        Node prev = null;

        while (cur != null) {
            if (set.contains(cur.data)) {
                prev.next = cur.next;
                cur = cur.next;
            } else {
                set.add(cur.data);
                prev = cur;
                cur = cur.next;
            }
        }
    }

    public static void main(String args[]) {
        LinkedList list = new LinkedList();
        for (int i = 1; i < 10; i++) {
            list.append(i);
        }
        list.insert(4, 4);
        list.insert(5, 5);
        list.insert(6, 2);
        list.print();

        RemoveDuplicates.solution(list);
        list.print();
    }
}