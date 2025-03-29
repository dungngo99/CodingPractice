package DynamicProgramming;

import java.util.Arrays;

public class ShortestLengthSum {
    public int[] findShortestLengthSum(int targetSum, int[] nums) {
        int[][] dp = new int[targetSum + 1][];
        dp[0] = new int[] {};

        for (int i = 0; i <= targetSum; i++) {
            if (dp[i] != null) {
                int[] curSum = dp[i];
                int curL = curSum.length;

                for (int num : nums) {
                    if (i + num >= dp.length)
                        continue;

                    int[] nextSum = dp[i + num];
                    if (nextSum == null || (nextSum != null && nextSum.length > curL + 1)) {
                        int[] newSum = Arrays.copyOfRange(curSum, 0, curL + 1);
                        newSum[curL] = num;
                        dp[i + num] = newSum;
                    }
                }
            }
        }
        return dp[targetSum];
    }

    public static void main(String[] args) {
        int[] sample = new int[] { 1, 2, 5, 25 };
        int N = 15;

        ShortestLengthSum bestSum = new ShortestLengthSum();
        int[] res = bestSum.findShortestLengthSum(N, sample);

        System.out.println(Arrays.toString(res));
    }
}
