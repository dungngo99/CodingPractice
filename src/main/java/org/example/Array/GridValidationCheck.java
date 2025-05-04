package org.example.Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridValidationCheck {

    public static void main(String[] args) {
        GridValidationCheck.runQuestion1();
        GridValidationCheck.runQuestion2();
    }

    public static void runQuestion1() {
        System.out.println(new GridValidationCheck().validateGrid1(new int[][]{
                {1, 2, 3},
                {3, 1, 2},
                {2, 3, 1}
        })); // true
        System.out.println(new GridValidationCheck().validateGrid1(new int[][]{
                {1, 3},
                {3, 1}
        })); // false
        System.out.println(new GridValidationCheck().validateGrid1(new int[][]{
                {-1, 3},
                {3, -1}
        })); // false
    }

    public static void runQuestion2() {
        System.out.println(new GridValidationCheck().validateGrid2(
                new char[][]{
                        {'B', 'W'},
                        {'W', 'B'}
                },
                List.of(List.of(1), List.of(1)),
                List.of(List.of(1), List.of(1)))
        ); // true
        System.out.println(new GridValidationCheck().validateGrid2(
                new char[][]{
                        {'B', 'B'},
                        {'B', 'B'}
                },
                List.of(List.of(2), List.of(2)),
                List.of(List.of(2), List.of(2)))
        ); // true
        System.out.println(new GridValidationCheck().validateGrid2(
                new char[][]{
                        {'B', 'B'},
                        {'W', 'B'}
                },
                List.of(List.of(1), List.of(1)),
                List.of(List.of(1), List.of(1)))
        ); // false
        System.out.println(new GridValidationCheck().validateGrid2(
                new char[][]{
                        {'W', 'B', 'B', 'W'},
                        {'B', 'B', 'W', 'W'},
                        {'W', 'B', 'W', 'B'}
                },
                List.of(List.of(2), List.of(2), List.of(1, 1)),
                List.of(List.of(1), List.of(3), List.of(1), List.of(1))
        )); // true
        System.out.println(new GridValidationCheck().validateGrid2(
                new char[][]{
                        {'B', 'B'},
                        {'B', 'W'},
                        {'B', 'B'}
                },
                List.of(List.of(2), List.of(1), List.of(2)),
                List.of(List.of(3), List.of(1))
        )); // false
        System.out.println(new GridValidationCheck().validateGrid2(
                new char[][]{
                        {'W', 'B', 'B'},
                        {'B', 'W', 'B'}
                },
                List.of(List.of(2), List.of(2)),
                List.of(List.of(1), List.of(3), List.of(1), List.of(1))
        )); // false
    }

    public boolean validateGrid1(int[][] grid) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            Set<Integer> rowVisited = new HashSet<>();
            Set<Integer> columnVisited = new HashSet<>();
            for (int j = 0; j < N; j++) {
                int rowCell = grid[i][j];
                int columnCell = grid[j][i];
                if (rowCell < 0 || rowCell > N || rowVisited.contains(rowCell)) {
                    return false;
                }
                if (columnCell < 0 || columnCell > N || columnVisited.contains(columnCell)) {
                    return false;
                }
                rowVisited.add(rowCell);
                columnVisited.add(columnCell);
            }
        }
        return true;
    }

    public boolean validateGrid2(char[][] grid, List<List<Integer>> rowActions, List<List<Integer>> columnActions) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            List<Integer> rowAction = new ArrayList<>(rowActions.get(i));
            int currentRowActionIdx = 0;
            int j;
            for (j = 0; j < n; j++) {
                char cell = grid[i][j];
                if (cell == 'W') {
                    continue;
                }
                if (currentRowActionIdx >= rowAction.size()) {
                    break;
                }
                rowAction.set(currentRowActionIdx, rowAction.get(currentRowActionIdx) - 1);
                if (rowAction.get(currentRowActionIdx) == 0) {
                    currentRowActionIdx++;
                }
            }
            if (currentRowActionIdx < rowAction.size() || j < n) {
                return false;
            }
        }

        for (int j = 0; j < n; j++) {
            List<Integer> columnAction = new ArrayList<>(columnActions.get(j));
            int currentColumnActionIdx = 0;
            int i;
            for (i = 0; i < m; i++) {
                char cell = grid[i][j];
                if (cell == 'W') {
                    continue;
                }
                if (currentColumnActionIdx >= columnAction.size()) {
                    break;
                }
                columnAction.set(currentColumnActionIdx, columnAction.get(currentColumnActionIdx) - 1);
                if (columnAction.get(currentColumnActionIdx) == 0) {
                    currentColumnActionIdx++;
                }
            }
            if (currentColumnActionIdx < columnAction.size() || i < m) {
                return false;
            }
        }
        return true;
    }
}
