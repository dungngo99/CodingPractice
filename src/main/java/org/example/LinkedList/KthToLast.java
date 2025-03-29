package org.example.LinkedList;

import java.util.Stack;

public class KthToLast {
	public static Node stack(Node node, int k) {
		Stack<Node> stack = new Stack<>();
		Node tmp = node;
		int l = 0;

		while (tmp != null) {
			stack.add(tmp);
			l++;
		}

		if (k <= 0 || k > l)
			return null;

		return stack.get(l - k);
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		for (int i = 1; i < 10; i++)
			list.append(i);
	}

}
