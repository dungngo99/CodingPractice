package org.example.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KSizedSubarray {
    public int[] findKSizedSubarray(int[] list, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < list.length; i++) {
            queue.add(list[i]);
            if (queue.size() > k)
                queue.poll();
        }

        int[] res = new int[queue.size()];
        for (int i = 0; i < k; i++)
            res[i] = queue.poll();
        return res;
    }

    public static void main(String args[]) {
        KSizedSubarray ks = new KSizedSubarray();
        int[] list = new int[] { 9, 2, 7, 6, 5, 4, 3, 2, 1 };
        int[] res = ks.findKSizedSubarray(list, 3);
        System.out.println(Arrays.toString(res));
    }
}
