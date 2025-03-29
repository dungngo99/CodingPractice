package org.example.LinkedList;

public class SumLinkedList {
	public static Node solution(Node node1, Node node2) {
		if (node1 == null && node2 == null)
			return null;
		if (node1 == null)
			return node2;
		if (node2 == null)
			return node1;

		Node newNode = new Node(node1.data + node2.data);
		newNode.next = solution(node1.next, node2.next);
		return newNode;
	}

	public static void main(String[] args) {
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();

		for (int i = 1; i < 10; i++) {
			list1.append(i);
			list2.append((int) Math.pow(10 - i, 2));
		}

		Node res = SumLinkedList.solution(list1.head, list2.head);
		Node.print(res);
	}
}
