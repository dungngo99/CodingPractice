package Backtracking;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

public class WordSearch {
    public static boolean search(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == word.charAt(0)) {
                    if (dfs(new int[] { row, col }, board, visited, 0, word))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int[] start, char[][] board, boolean[][] visited, int i, String word) {
        int x = start[0], y = start[1];
        int row = board.length, col = board[0].length;

        if (i == word.length() - 1)
            return true;
        if (visited[x][y] || word.charAt(i) != board[x][y])
            return false;

        visited[x][y] = true;
        int[][] choices = new int[][] { { x - 1, y }, { x + 1, y }, { x, y + 1 }, { x, y - 1 } };

        List<int[]> neighbors = Arrays.stream(choices)
                .filter(a -> 0 <= a[0] && a[0] < row && 0 <= a[1] && a[1] < col)
                .filter(a -> board[a[0]][a[1]] == word.charAt(i + 1))
                .filter(a -> !visited[a[0]][a[1]])
                .collect(Collectors.toList());

        for (int[] neighbor : neighbors) {
            if (dfs(neighbor, board, visited, i + 1, word)) {
                return true;
            }
        }

        visited[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' } };
        String word = "ABCCEESE";
        word = "ABFSADECCESE";
        System.out.println(WordSearch.search(board, word));
    }
}