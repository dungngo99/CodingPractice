package org.example.DynamicProgramming;

/*
 * We have a grid=(n,n), start=(n-1,0), end=(0,n-1).
 * We can go only either up or right. Also, the right bottom half of the grid is restricted. 
 * Question: Find the number of unique paths from start to end?
 * Solution:
 *  Without lossing the generality, we can reform the problem as the left bottom half is restricted.
 *  start=(0,0) and end=(n-1,n-1). At each step, there are 2 paths to reach it (from top->down or left->right)
 *  We use Bottom-up DP: 3 cases
 *  1. general: grid(i,j) = grid(i-1,j) + grid(i,j-1)
 *  2. on top edge (i=0), we can only go right, not up: grid(0,j) = grid(0,j-1) = 1
 *  3. on diagonal edge (j=n-i), we can only go down, not right: grid(i,j) = grid(i-1,j)
 */
public class RestrictedGridLand {
    public int findUniquePaths(int n) {
        int[][] grid = new int[n][n];
        for (int col = 0; col < n; col++)
            grid[0][col] = 1;

        for (int row = 1; row < n; row++) {
            for (int col = row; col < n; col++) {
                if (row == col)
                    grid[row][col] = grid[row - 1][col];
                else
                    grid[row][col] += grid[row - 1][col] + grid[row][col - 1];
            }
        }
        return grid[n - 1][n - 1];
    }

    public static void main(String[] args) {
        RestrictedGridLand uniquePaths = new RestrictedGridLand();
        System.out.println(uniquePaths.findUniquePaths(5));
    }
}
