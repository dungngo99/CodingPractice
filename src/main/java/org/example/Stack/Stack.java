package org.example.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack<T> {
    private final List<T> stack;

    public Stack() {
        stack = new ArrayList<>();
    }

    public T pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
    }

    public T peek() {
        if (!stack.isEmpty()) {
            return stack.get(stack.size() - 1);
        }
        return null;
    }

    public void push(T element) {
        stack.add(element);
    }

    @Override
    public String toString() {
        return Arrays.toString(stack.toArray());
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(2);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        Stack<String> stackStr = new Stack<>();
        stackStr.push("Hello");
        stackStr.push("World");
        stackStr.push("Wow");
        System.out.println(stackStr.peek());
        System.out.println(stackStr.pop());
        System.out.println(stackStr);
    }
}