package Array;

import java.util.Arrays;

public class TwoSum {
    public int[] computeTwoSum(int[] array, int sum) {
        int[] res = new int[2];
        Arrays.sort(array);

        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            if (array[i] + array[j] < sum)
                i += 1;
            else if (array[i] + array[j] > sum)
                j -= 1;
            else {
                res[0] = array[i];
                res[1] = array[j];
                break;
            }
        }

        return res;
    }

    public int pairs(int k, int[] arr) {
        Arrays.sort(arr);

        int i = 0;
        int j = 0;
        int count = 0;
        while (i < arr.length && j < arr.length) {
            if (arr[j] - arr[i] < k)
                j += 1;
            else if (arr[j] - arr[i] > k)
                i += 1;
            else {
                count += 1;
                i += 1;
                j += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 4, 5, 1, 0, 9, 8, 3, 2, 1 };
        TwoSum twoSum = new TwoSum();
        int[] res = twoSum.computeTwoSum(nums, 10);
        System.out.println(Arrays.toString(res));

        int[] array = new int[] { 1, 5, 3, 4, 2 };
        System.out.println(twoSum.pairs(2, array));
    }
}
