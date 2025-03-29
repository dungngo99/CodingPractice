package org.example.Array;

public class ZeroMatrix {
    public static void main(String[] args) {
        ZeroMatrix solution = new ZeroMatrix();
        int[][] matrix = new int[][]{{1, 1, 0, 1}, {1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        solution.fillZeroMatrix(matrix);

    }

    public void fillZeroMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean fillRearLeft = false;
        boolean fillRearUp = false;

        // step 1: mark the matrix for filling up with zero later
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                fillRearLeft = true;
                break;
            }
        }

        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                fillRearUp = true;
                break;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // step 2: fill the matrix with zeros
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (fillRearLeft) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }

        if (fillRearUp) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}