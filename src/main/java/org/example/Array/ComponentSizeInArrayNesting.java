package Array;

import java.util.*;

/*
 * Given an oscillating (nested) array, 
 * we want to find the size of largest connected components
 */
public class ComponentSizeInArrayNesting {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int idx = i;

                // detect a connected component
                Set<Integer> set = new HashSet<>();
                while (!set.contains(nums[idx])) {
                    visited[idx] = true;
                    set.add(nums[idx]);
                    idx = nums[idx];
                }

                int count = 1;
                int start = nums[idx];
                int next = nums[start];

                // count the size of that connected component
                while (start != next) {
                    next = nums[next];
                    count += 1;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ComponentSizeInArrayNesting solution = new ComponentSizeInArrayNesting();
        System.out.println(solution.arrayNesting(new int[] { 5, 4, 0, 1, 3, 6, 2, 7, 8 }));
        System.out.println(solution.arrayNesting(new int[] { 2, 1, 3, 5, 4, 6, 8, 7, 9, 0 }));
    }
}