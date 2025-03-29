package org.example.GraphTheory;

import java.util.Arrays;

public class SegmentTreeArray {
    public static int[] constructTree(int[] array) {
        int base = ((int) (Math.log(array.length) / Math.log(2))) + 1;
        int length = ((int) Math.pow(2, base));

        int[] tree = new int[length * 2 - 1];
        Arrays.fill(tree, -1);

        constructTreeHelper(array, tree, 0, array.length - 1, 0);
        return tree;
    }

    public static void constructTreeHelper(int[] array, int[] tree, int low, int high, int pos) {
        if (low == high) {
            tree[pos] = array[low];
            return;
        }

        int mid = (low + high) / 2;
        constructTreeHelper(array, tree, low, mid, left(pos));
        constructTreeHelper(array, tree, mid + 1, high, right(pos));
        tree[pos] = Math.min(tree[left(pos)], tree[right(pos)]);
    }

    public static int rangeQuery(int[] tree, int[] array, int[] range) {
        return rangeQueryHelper(tree, range[0], range[1], 0, array.length - 1, 0);
    }

    public static int rangeQueryHelper(int[] tree, int x, int y, int a, int b, int pos) {
        if (x <= a && b <= y)
            return tree[pos];
        if (y < a || x > b)
            return Integer.MAX_VALUE;

        int mid = (a + b) / 2;
        int leftValue = rangeQueryHelper(tree, x, y, a, mid, left(pos));
        int rightValue = rangeQueryHelper(tree, x, y, mid + 1, b, right(pos));
        return Integer.min(leftValue, rightValue);
    }

    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 5, 8, 6, 3, 2, 7, 1 };
        int[] tree = SegmentTreeArray.constructTree(nums);
        System.out.println(Arrays.toString(tree));

        int[] query = { 5, 7 };
        System.out.println("Query range[5, 7]=" + SegmentTreeArray.rangeQuery(tree, nums, query));
    }
}
