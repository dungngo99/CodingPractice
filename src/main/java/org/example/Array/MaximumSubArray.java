package Array;

public class MaximumSubArray {
    public int solution(int[] nums) {
        int res = nums[0];
        int cSum = res;

        for (int i = 1; i < nums.length; i++) {
            if (cSum <= 0)
                cSum = nums[i];
            else
                cSum += nums[i];
            res = Math.max(res, cSum);
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumSubArray solution = new MaximumSubArray();
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(solution.solution(nums));
    }
}