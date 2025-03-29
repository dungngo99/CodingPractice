package org.example.Stack;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class MaxInWindow {
    public static void findMaxInWindow(int[] arrays, int w) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max = arrays[0];

        for (int i = 0; i < w; i++) {
            queue.add(-arrays[i]);
            max = Math.max(max, arrays[i]);
        }

        for (int i = 1; i <= arrays.length - w; i++) {
            queue.remove(-arrays[i - 1]);
            queue.add(-arrays[i + w - 1]);
        }

        System.out.println(Arrays.toString(arrays));
    }

    public static void main(String[] args) {
        int[] arrays = new int[] { 2, 1, 2, 3, 4, 2, 1, 14, 2, 2, 16, 7, 8, 9, -5, -1, 3 };
        MaxInWindow.findMaxInWindow(arrays, 4);
    }
}