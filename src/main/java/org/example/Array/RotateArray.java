package org.example.Array;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        RotateArray solution = new RotateArray();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(array));
        solution.rotateOne(array, 100);
        System.out.println(Arrays.toString(array));

        array = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(array));
        solution.rotateTwo(array, 100);
        System.out.println(Arrays.toString(array));
    }

    public void rotateOne(int[] nums, int k) {
        k = k % nums.length;
        int[] temp = new int[k];

        //System.arraycopy is more efficient than Arrays.copyOf.
        //System.arraycopy copies 1 existing array to another existing array
        //Arrays.copyOf copy and create a new copy
        System.arraycopy(nums, nums.length - k, temp, 0, k);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(temp, 0, nums, 0, k);
    }

    public void rotateTwo(int[] nums, int k) {
        k = k % nums.length;
        int count = nums.length;

        int next;
        int i = 0;
        int t = nums[i];
        while (count > 0) {
            next = (i + k) % nums.length;
            int temp = nums[next];
            nums[next] = t;
            i = next;
            t = temp;
            count -= 1;
        }
    }
}