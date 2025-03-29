package org.example.GraphTheory;

import java.util.HashSet;
import java.util.Set;

public class DFS {

    public static void traverseDFS(int[][] graph, Set<Integer> visited, int curNode) {
        if (visited.contains(curNode))
            return;

        visited.add(curNode);
        System.out.println("This is node " + curNode);
        for (int neighbor : graph[curNode]) {
            traverseDFS(graph, visited, neighbor);
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] { { 1, 3 }, { 0, 4 }, { 1, 4 }, { 0 }, { 1, 2 } };
        Set<Integer> visited = new HashSet<>();
        traverseDFS(graph, visited, 0);
    }
}
