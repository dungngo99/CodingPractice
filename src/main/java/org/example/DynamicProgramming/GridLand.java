package DynamicProgramming;

import java.util.*;

/*
 * Given an 2D array, we start at (0,0) and end at (x,y). We can move vertically down or horizontally right
 * If we move vertically down, we add "V" to a path. 
 * If we move horizontally right, add "H" to a path
 * Return the value at index k from a list of paths.
 */
public class GridLand {
    public static List<List<String>> findGridLandAtK(int[][] journeys) {
        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < journeys.length; i++) {
            int dx = journeys[i][0];
            int dy = journeys[i][1];

            List<String> paths = new ArrayList<>();
            GridLand.findPaths(paths, new StringBuilder(), new int[] { 1, 1 }, new int[] { dx, dy });
            res.add(paths);
        }
        return res;
    }

    public static void findPaths(List<String> paths, StringBuilder path, int[] cur, int[] des) {
        if (cur[0] == des[0] && cur[1] == des[1]) {
            paths.add(path.toString());
            return;
        }

        if (cur[0] + 1 <= des[0]) {
            StringBuilder newPath = new StringBuilder(path);
            newPath.append("V");
            findPaths(paths, newPath, new int[] { cur[0] + 1, cur[1] }, des);
        }
        if (cur[1] + 1 <= des[1]) {
            StringBuilder newPath = new StringBuilder(path);
            newPath.append("H");
            findPaths(paths, newPath, new int[] { cur[0], cur[1] + 1 }, des);
        }
    }

    public static void main(String[] args) {
        int[][] journeys = new int[][] { { 2, 2 }, { 1, 1 }, { 4, 5 } };
        List<List<String>> result = GridLand.findGridLandAtK(journeys);
        System.out.println(result);
    }
}
