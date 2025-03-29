package org.example.DynamicProgramming;

public class CanSumToTarget {
    public boolean bottomUp(int value, int[] nums) {
        boolean[] dp = new boolean[value + 1];
        dp[0] = true;

        for (int i = 0; i < value; i++) {
            if (!dp[i])
                continue;

            for (int num : nums) {
                if (num + i < dp.length)
                    dp[num + i] = true;
            }
        }
        return dp[value];
    }

    public static void main(String[] args) {
        CanSumToTarget canSum = new CanSumToTarget();
        System.out.println(canSum.bottomUp(7, new int[] { 3, 4, 5 }));
        System.out.println(canSum.bottomUp(11, new int[] { 2, 4, 5, 6 }));
        System.out.println(canSum.bottomUp(10000, new int[] { 5, 6, 7, 8, 10 }));
    }
}
