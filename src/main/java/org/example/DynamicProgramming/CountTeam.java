package org.example.DynamicProgramming;

public class CountTeam {
    public int numTeams(int[] rating) {
        int count = 0;
        int[] rRating = new int[rating.length];

        for (int i = 0; i < rating.length; i++) {
            rRating[i] = rating[rating.length - 1 - i];
        }

        count += countTeams(rating);
        count += countTeams(rRating);

        return count;
    }

    public int countTeams(int[] rating) {
        int[] dp = new int[rating.length];
        for (int i = 0; i < rating.length; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i])
                    count += 1;
            }
            dp[i] = count;
        }

        int count = 0;
        for (int i = 2; i < rating.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) {
                    count += dp[j];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountTeam solution = new CountTeam();
        System.out.println(solution.numTeams(new int[] { 2, 5, 3, 4, 1, 6, 8 }));
        System.out.println(solution.numTeams(new int[] { 2, 1, 3 }));
        System.out.println(solution.numTeams(new int[] { 1, 2, 3, 4 }));
    }
}