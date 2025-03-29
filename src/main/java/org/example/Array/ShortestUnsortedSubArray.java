package org.example.Array;

import java.util.Arrays;

public class ShortestUnsortedSubArray {
    public static void main(String[] args) {
        ShortestUnsortedSubArray solution = new ShortestUnsortedSubArray();
        int[] list = new int[]{2, 6, 4, 8, 10, 9, 15};
        System.out.println(solution.findUnsortedSubarray(list));

        list = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(solution.findUnsortedSubarray(list));

        list = new int[]{1, 4, 2, 1, 4, 6, 2, 7, 24, 5, 3, 236, 7, 3, 5, 3, 43, 2};
        System.out.println(solution.findUnsortedSubarray(list));
    }

    public int findUnsortedSubarray(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        boolean fixedI = false;
        boolean fixedJ = false;

        while (i < j && (!fixedI || !fixedJ)) {
            if (!fixedI) {
                if (nums[i] > nums[i + 1])
                    fixedI = true;
                else
                    i += 1;
            }
            if (!fixedJ) {
                if (nums[j] < nums[j - 1])
                    fixedJ = true;
                else
                    j -= 1;
            }
        }
        if (i >= j)
            return 0;

        int[] sorted = Arrays.copyOfRange(nums, i, j + 1);
        Arrays.sort(sorted);
        int count = j - i + 1;

        int head = i - 1;
        int tail = j + 1;
        while (head >= 0 && nums[head] > sorted[0]) {
            count += 1;
            head -= 1;
        }
        while (tail < nums.length && nums[tail] < sorted[sorted.length - 1]) {
            count += 1;
            tail += 1;
        }

        return count;
    }
}