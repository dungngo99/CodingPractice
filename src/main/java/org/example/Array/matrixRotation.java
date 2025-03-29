package Array;

public class matrixRotation {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            rotate(mat);
            if (compareMatrix(mat, target))
                return true;
        }
        return false;
    }

    public void rotate(int[][] mat) {
        int row = mat.length;

        for (int i = 0; i < row / 2; i++) {
            int low = i;
            int high = row - 1 - i;
            for (int j = i; j < high; j++) {
                int t = mat[j][high];

                mat[j][high] = mat[low][j];
                mat[low][j] = mat[row - 1 - j][low];
                mat[row - 1 - j][low] = mat[high][row - 1 - j];
                mat[high][row - 1 - j] = t;
            }
        }
    }

    public boolean compareMatrix(int[][] m1, int[][] m2) {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                if (m1[i][j] != m2[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] m1 = new int[][] { { 0, 1, 0, 0 }, { 0, 0, 0, 1 }, { 0, 1, 0, 0 }, { 0, 1, 1, 1 } };
        int[][] m2 = new int[][] { { 0, 1, 0, 1 }, { 0, 0, 0, 1 }, { 1, 0, 1, 1 }, { 0, 0, 0, 0 } };
        System.out.println(new matrixRotation().findRotation(m1, m2));
    }
}