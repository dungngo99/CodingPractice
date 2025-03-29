package Sorting;

import java.util.Arrays;

public class MergeSort {
    public int[] mergeSort(int[] array){
        if (array.length == 1) return array;

        int mid = array.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(array, mid, array.length));

        int[] combined = new int[array.length];
        int i = 0; int j = 0; int k = 0;

        while (i < left.length && j < right.length){
            if (left[i] < right[j]){
                combined[k] = left[i++];
                k += 1;
            }else if (left[i] > right[j]){
                combined[k] = right[j++];
                k += 1;
            }else{
                combined[k] = right[j++];
                combined[k+1] = left[i++];
                k += 2;
            }
        }

        for (int x=i; x<left.length; x++){
            combined[k] = left[x];
            k += 1;
        }
        for (int x=j; x<right.length; x++){
            combined[k] = right[x];
            k += 1;
        }

        return combined;
    }

    public static void main(String[] args){
        int[] lst = new int[]{1,4,5,2,0,1,2,4,7,2,8,9,10,2,3,4};
        MergeSort sorting = new MergeSort();
        int[] sortedLst = sorting.mergeSort(lst);
        System.out.println(Arrays.toString(sortedLst));
    }
}
