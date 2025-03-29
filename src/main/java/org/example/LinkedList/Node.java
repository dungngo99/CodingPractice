package org.example.LinkedList;

import java.util.StringJoiner;

public class Node {
	protected Node next;
	protected Node prev;
	protected int data;

	public Node(int data) {
		this.data = data;
	}

	public static void print(Node node) {
		StringJoiner joiner = new StringJoiner("->");
		while (node != null) {
			joiner.add(String.valueOf(node.data));
			node = node.next;
		}
		System.out.println(joiner.toString());
	}
}
