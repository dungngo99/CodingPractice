package org.example.LinkedList;

import java.util.Stack;

public class Palindrome {
	public static boolean solution(Node head) {
		Node tmp = head;
		Stack<Integer> stack = new Stack<>();

		while (tmp != null) {
			stack.add(tmp.data);
			tmp = tmp.next;
		}

		tmp = head;
		while (stack.size() > 0) {
			if (tmp.data != stack.pop())
				return false;
			tmp = tmp.next;
		}

		return true;
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 5; i++) {
			list.append(i);
		}
		Palindrome.solution(list.head);
	}
}
