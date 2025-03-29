package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Runtime: O(m^2*n) with m=value of targetSum, n=length of list of numbers
 */
public class ListOfNumbersToSum {
    public static Set<List<Integer>> bottomUp(int targetSum, int[] nums) {
        List<Set<List<Integer>>> dp = new ArrayList<>();
        for (int i = 0; i <= targetSum; i++)
            dp.add(new HashSet<>());

        dp.get(0).add(List.of());

        for (int i = 0; i < targetSum + 1; i++) {
            if (dp.get(i).size() == 0)
                continue;

            for (int num : nums) {
                if (num + i > targetSum)
                    continue;

                for (List<Integer> curList : dp.get(i)) {
                    List<Integer> newList = new ArrayList<>(curList);
                    newList.add(num);
                    Collections.sort(newList);
                    dp.get(i + num).add(newList);
                }
            }
        }
        return dp.get(targetSum);
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 11, 12, 13, 14 };
        int N = 33;
        Set<List<Integer>> res = ListOfNumbersToSum.bottomUp(N, nums);
        System.out.println(res);
    }
}
