package org.example.GraphTheory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ConnectedCell {
    public int solution(int[][] matrix){
        int row = matrix.length; int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        int maxArea = 0;

        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (visited[i][j] || matrix[i][j] == 0){
                    visited[i][j] = true;
                    continue;
                }

                int area = 1;
                Queue<Integer[]> frontiers = new LinkedList<>();

                frontiers.add(new Integer[]{i, j});
                visited[i][j] = true;

                while (!frontiers.isEmpty()){
                    Integer[] curNode = frontiers.poll();
                    List<Integer[]> neighbors = findNeighbors(matrix, visited, curNode);

                    for (Integer[] neighbor: neighbors){
                        frontiers.add(neighbor);
                        visited[neighbor[0]][neighbor[1]] = true;
                        area += 1;
                    }
                }

                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public List<Integer[]> findNeighbors(int[][] matrix, boolean[][] visited, Integer[] node){
        int x = node[0]; int y = node[1];
        int row = matrix.length; int col = matrix[0].length;
        List<Integer[]> choices = List.of(new Integer[]{x,y-1}, new Integer[]{x-1,y}, new Integer[]{x+1,y}, new Integer[]{x,y+1});

        List<Integer[]> newChoices = choices.stream().filter(a -> 0 <= a[0] && a[0] < row && 0 <= a[1] && a[1] < col).collect(Collectors.toList());
        newChoices = newChoices.stream().filter(a -> matrix[a[0]][a[1]] == 1).collect(Collectors.toList());
        newChoices = newChoices.stream().filter(a -> !visited[a[0]][a[1]]).collect(Collectors.toList());
        return newChoices;
    }

    public static void main(String[] args) {
        ConnectedCell solution = new ConnectedCell();
        int[][] matrix = new int[][]{{1,1,0,0}, {0,1,1,0}, {0,0,1,0}, {1,0,0,0}};
        System.out.println(solution.solution(matrix));
    }
}