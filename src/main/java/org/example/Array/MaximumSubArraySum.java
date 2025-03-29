package org.example.Array;

import java.util.Arrays;

public class MaximumSubArraySum {
    static long maximumSum(long[] a, long m) {
        long max = Arrays.stream(a).max().getAsLong();
        if (max == m - 1)
            return max;

        long[] lst1 = new long[a.length];
        long[] lst2 = a;

        for (int i = 0; i < a.length; i++) {
            long[] temp = new long[a.length];
            for (int j = i + 1; j < a.length; j++) {
                temp[j] = lst2[j] + lst2[j - 1] - lst1[j - 1];
                max = Math.max(max, temp[j] % m);
                if (max == m - 1)
                    return max;
            }
            lst1 = lst2;
            lst2 = temp;
        }
        return max;
    }

    public static void main(String[] args) {
        long[] array = new long[]{1L, 5L, 9L};
        array = new long[]{1L, 2L, 3L};
        System.out.println(MaximumSubArraySum.maximumSum(array, 4));
    }
}