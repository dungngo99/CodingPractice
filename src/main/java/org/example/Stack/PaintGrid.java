package org.example.Stack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class PaintGrid {
    public static class Action {
        private final String actionType;
        private final int[] twoCoords;
        private final int[] fourCoords;
        private final char color;
        public Action(String actionType, char color, int[] twoCoords, int[] fourCoords) {
            this.actionType = actionType;
            this.color = color;
            this.twoCoords = twoCoords;
            this.fourCoords = fourCoords;
        }

        @Override
        public String toString() {
            return "Action{" +
                    "actionType='" + actionType + '\'' +
                    ", twoCoords=" + Arrays.toString(twoCoords) +
                    ", fourCoords=" + Arrays.toString(fourCoords) +
                    ", color=" + color +
                    '}';
        }
    }

    private final int m;
    private final int n;
    private final char[][] grid;
    private final Stack<Action> redoStack;
    private final Stack<Action> undoStack;

    /**
     * Initialize the painting service on the grid
     * @param m the row size of the grid
     * @param n the column size of the grid
     */
    public PaintGrid(int m, int n) {
        this.m = m;
        this.n = n;
        this.grid = new char[m][n];
        fillGridWithWhite();
        this.redoStack = new Stack<>();
        this.undoStack = new Stack<>();
    }

    private void fillGridWithWhite() {
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = 'W';
            }
        }
    }

    /**
     * Color the cell grid
     * @param i x coordinate of the cell in 2D
     * @param j y coordinate of the cell in 2D
     * @param color color to be painted
     */
    public void drawCell(int i, int j, char color) {
        drawCell(i, j, color, true);
    }

    private void drawCell(int i, int j, char color, boolean isTrack) {
        grid[i][j] = color;
        if (isTrack) {
            redoStack.push(new Action("drawCell", color, new int[]{i, j}, null));
        }
    }

    /**
     * Color a specific area of the grid
     * @param i1 x coordinate of the top-left cell in 2D
     * @param j1 y coordinate of the top-left cell in 2D
     * @param i2 x coordinate of the bottom-right cell in 2D
     * @param j2 y coordinate of the bottom-right cell in 2D
     * @param color color to be painted
     */
    public void drawRectangle(int i1, int j1, int i2, int j2, char color) {
        drawRectangle(i1, j1, i2, j2, color, true);
    }

    private void drawRectangle(int i1, int j1, int i2, int j2, char color, boolean isTrack) {
        int minX = Math.min(i1, i2);
        int maxX = Math.max(i1, i2);
        int minY = Math.min(j1, j2);
        int maxY = Math.max(j1, j2);
        for (int i=minX; i<=maxX; i++) {
            for (int j=minY; j<=maxY; j++) {
                grid[i][j] = color;
            }
        }
        if (isTrack) {
            redoStack.push(new Action("drawRectangle", color, null, new int[]{i1, j1, i2, j2}));
        }
    }

    /**
     * Undo the previous painting action
     * Use stack to keep track of redo actions
     */
    public void undo() {
        if (redoStack.isEmpty()) {
            return;
        }
        Action action = redoStack.pop();
        String actionType = action.actionType;
        System.out.println(action);
        if (Objects.equals(actionType, "drawCell")) {
            int[] c = action.twoCoords;
            drawCell(c[0], c[1], 'W', false); // because revert, no need to add to redo stack
            undoStack.push(new Action(actionType, action.color, new int[]{c[0], c[1]}, null));
        } else {
            int[] c = action.fourCoords;
            drawRectangle(c[0], c[1], c[2], c[3], 'W', false); // because revert, no need to add to redo stack
            undoStack.push(new Action(actionType, action.color, null, new int[]{c[0], c[1], c[2], c[3]}));
        }
    }

    /**
     * Redo the previous painting action
     * Use stack to keep track of undo actions
     */
    public void redo() {
        if (undoStack.isEmpty()) {
            return;
        }
        Action action = undoStack.pop();
        String actionType = action.actionType;
        char color = action.color;
        if (Objects.equals(actionType, "drawCell")) {
            int[] c = action.twoCoords;
            drawCell(c[0], c[1], color);
        } else {
            int[] c = action.fourCoords;
            drawRectangle(c[0], c[1], c[2], c[3], color);
        }
        redoStack.push(action);
    }

    /**
     * Show the current state of the grid to standard output
     */
    public void paint() {
        for (int i=0; i<m; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j=0; j<n; j++) {
                builder.append(grid[i][j]).append(", ");
            }
            System.out.println(builder);
        }
    }

    /**
     * Convert the grid back to the original state, all whites
     */
    public void clear() {
        redoStack.clear();
        undoStack.clear();
        fillGridWithWhite();
    }

    public static void main(String[] args) {
        System.out.println("\n Begin to pain the grid.....\n");
        PaintGrid pg = new PaintGrid(3, 3);
        System.out.println("\n Draw cell....\n");
        pg.drawCell(0, 0, 'R');
        System.out.println("\n Draw rectangle...\n");
        pg.drawRectangle(1, 1, 2, 2, 'B');
        System.out.println("\n Print the grid...\n");
        pg.paint();
        System.out.println("\n Undo grid (twice)...\n");
        pg.undo();
        pg.undo();
        System.out.println("\n Print the grid (p2)...\n");
        pg.paint();
        System.out.println("\n Redo grid (once)...\n");
        pg.redo();
        System.out.println("\n Draw cell....\n");
        pg.drawCell(2,0, 'B');
        System.out.println("\n Print the grid (p3)...\n");
        pg.paint();
        System.out.println("\n Clear the grid...\n");
        pg.clear();
        System.out.println("\n Print the grid (p4)...\n");
        pg.paint();
    }
}
