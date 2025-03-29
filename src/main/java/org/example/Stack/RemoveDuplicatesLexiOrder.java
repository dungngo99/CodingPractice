package org.example.Stack;

import java.util.Stack;
import java.util.stream.Collectors;

/*
Visa OA
Given a string s, remove duplicate letters so that every letter appears once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:
Input s = 'bcabc'
Output 'abc'

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"
 */

public class RemoveDuplicatesLexiOrder {
    public static String RemoveDuplicate(String string) {
        boolean[] visited = new boolean[26];
        int[] lastIndices = new int[26];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            int charIndex = string.charAt(i) - 'a';
            lastIndices[charIndex] = i;
        }

        for (int i = 0; i < string.length(); i++) {
            char curChar = string.charAt(i);

            if (!visited[curChar - 'a']) {
                while (stack.size() > 0 && stack.peek() >= curChar && lastIndices[stack.peek() - 'a'] > i) {
                    char removedChar = stack.pop();
                    visited[removedChar - 'a'] = false;
                }

                stack.push(curChar);
                visited[curChar - 'a'] = true;
            }
        }

        return stack.stream().map(e -> e.toString()).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String s = "bcabc";
        System.out.println(RemoveDuplicate(s));
    }
}
