package DynamicProgramming;

import java.util.*;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> list : dp) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(num);
                tmp.add(newList);
            }
            dp.addAll(tmp);
        }
        return dp;
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();
        int[] nums = new int[] { 1, 2, 3, 4 };
        List<List<Integer>> res = solution.subsets(nums);
        System.out.println(res);
    }
}