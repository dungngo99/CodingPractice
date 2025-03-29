package org.example.DynamicProgramming;

import java.util.Arrays;

public class Knapsack {
    public static boolean[] bottomUpWithDuplicates(int[] weights) {
        int length = weights.length;
        int sum = Arrays.stream(weights).reduce((int val, int red) -> val + red).getAsInt();
        boolean[][] table = new boolean[length + 1][sum + 1];
        table[0][0] = true;

        for (int k = 1; k <= length; k++) {
            int curWeight = weights[k - 1];
            for (int n = 0; n <= sum; n++) {
                boolean up = k >= 1 ? table[k - 1][n] : false;
                boolean diag = n >= curWeight ? table[k - 1][n - curWeight] : false;
                boolean left = n >= curWeight ? table[k][n - curWeight] : false;
                table[k][n] = left || up || diag;
            }
        }
        return table[length];
    }

    public static boolean[] bottomUpWithoutDuplicates(int[] weights) {
        int length = weights.length;
        int sum = Arrays.stream(weights).reduce((int value, int red) -> value + red).getAsInt();
        boolean[][] table = new boolean[length + 1][sum + 1];
        table[0][0] = true;

        for (int k = 1; k <= length; k++) {
            int curWeight = weights[k - 1];
            for (int n = 0; n <= sum; n++) {
                boolean diag = k >= 1 && n >= curWeight ? table[k - 1][n - curWeight] : false;
                boolean up = k >= 1 ? table[k - 1][n] : false;
                table[k][n] = diag || up;
            }
        }
        return table[length];
    }

    public static boolean[] bottomUpToNum(int[] weights, int N) {
        int length = weights.length;
        boolean[][] table = new boolean[length + 1][N + 1];
        table[0][0] = true;

        for (int k = 1; k <= length; k++) {
            int weight = weights[k - 1];
            for (int num = 0; num <= N; num++) {
                boolean up = k >= 1 ? table[k - 1][num] : false;
                boolean diag = k >= 1 && num >= weight ? table[k - 1][num - weight] : false;
                table[k][num] = up || diag;
            }
        }
        return table[length];
    }

    public static void print(boolean[] cansum) {
        for (int i = 0; i < cansum.length; i++) {
            System.out.println("num=" + i + "-canForm=" + cansum[i]);
        }
    }

    public static void main(String[] args) {
        int[] weights = new int[] { 3, 3, 5 };

        boolean[] canSum = Knapsack.bottomUpWithDuplicates(weights);
        Knapsack.print(canSum);
        
        canSum = Knapsack.bottomUpWithoutDuplicates(weights);
        Knapsack.print(canSum);
    }

}
