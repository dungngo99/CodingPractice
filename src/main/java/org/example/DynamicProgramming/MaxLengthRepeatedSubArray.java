package org.example.DynamicProgramming;

public class MaxLengthRepeatedSubArray {
    public int findLengthByEnd(int[] A, int[] B){
        int res = 0;
        int lenA = A.length; int lenB = B.length;
        int[][] dp = new int[lenA+1][lenB+1];

        for (int i=0; i<lenA+1; i++){
            for (int j=0; j<lenB+1; j++){
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (A[i-1] == B[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public int findLengthByStart(int[] A, int[] B){
        int res = 0;
        int lenA = A.length; int lenB = B.length;
        int[][] dp = new int[lenA+1][lenB+1];

        // OST: if A[i] == B[j], then dp[i][j] = 1 + dp[i+1][j+1], else dp[i][j] = 0
        // Idea: if A[i] equals B[j], then A[i] & B[j] can be a start of a common substring.
        for (int i=lenA; i >= 0; i-- ){
            for (int j=lenB; j >= 0; j--){
                if (i == lenA || j == lenB){
                    dp[i][j] = 0;
                }else if (A[i] == B[j]){
                    dp[i][j] = 1 + dp[i+1][j+1];
                }else{
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxLengthRepeatedSubArray solution = new MaxLengthRepeatedSubArray();
        System.out.println(solution.findLengthByEnd(new int[]{1,1,1,1,1,1}, new int[]{1,1,1,1,1,1}));
        System.out.println(solution.findLengthByEnd(new int[]{0,0,0,0,0,0,1,0,0,0}, new int[]{0,0,0,0,0,0,0,1,0,0}));
        System.out.println(solution.findLengthByStart(new int[]{0,0,0,0,0,0,1,0,0,0}, new int[]{0,0,0,0,0,0,0,1,0,0}));
    }
}