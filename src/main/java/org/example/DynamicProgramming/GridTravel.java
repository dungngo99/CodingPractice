package DynamicProgramming;

public class GridTravel {
    public int findGridTravel(int row, int col) {
        int[][] dp = new int[row][col];

        // fill the rear (vertical left rear and horizontal up rear)
        for (int i = 0; i < col; i++)
            dp[0][i] = 1;
        for (int i = 0; i < row; i++)
            dp[i][0] = 1;

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        GridTravel gridTravel = new GridTravel();
        System.out.println(gridTravel.findGridTravel(4, 3));
        System.out.println(gridTravel.findGridTravel(45, 50));
        System.out.println(gridTravel.findGridTravel(80, 100));
    }
}
