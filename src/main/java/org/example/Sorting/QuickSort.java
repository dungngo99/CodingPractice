package Sorting;

import java.util.Arrays;

public class QuickSort {
    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void quicksort(int[] array, int left, int right) {
        // base case: the current size of array is 0 or 1
        if (right - left <= 0)
            return;

        // Generate a pivot index randomly
        int pivot = left + (int) (Math.random() * (right - left + 1));
        swap(array, left, pivot);

        pivot = array[left];
        int i = left + 1;
        for (int j = left + 1; j < right + 1; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }

        // move the pivot to its correct position
        swap(array, left, i - 1);

        // continue sorting the left partition and right partition
        quicksort(array, left, i - 2);
        quicksort(array, i, right);
    }

    public static void main(String[] args) {
        int[] array = new int[] { 0, 2, -3, -4, -4, -5, 3, -1, -2, 4, 6 };
        quicksort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
