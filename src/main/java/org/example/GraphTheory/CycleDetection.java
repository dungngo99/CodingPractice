package org.example.GraphTheory;

public class CycleDetection {

    public static boolean detectCycleDirected(int[][] graph, int source) {
        boolean[] visited = new boolean[graph.length];
        return helperDirected(graph, source, visited);
    }

    private static boolean helperDirected(int[][] graph, int source, boolean[] visited) {
        if (visited[source])
            return true;

        visited[source] = true;
        for (int neighbor : graph[source]) {
            if (helperDirected(graph, neighbor, visited))
                return true;
        }
        visited[source] = false;

        return false;
    }

    public static boolean detectCycleUndirected(int[][] graph, int source) {
        boolean[] visited = new boolean[graph.length];
        return helperUndirected(graph, source, visited, source);
    }

    private static boolean helperUndirected(int[][] graph, int source, boolean[] visited, int parent) {
        if (visited[source])
            return true;

        visited[source] = true;
        for (int neighbor : graph[source]) {
            if (neighbor != parent && helperUndirected(graph, neighbor, visited, source))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] directedGraph = new int[][] { { 1, 2 }, { 2, 3 }, {}, { 4 }, { 1 } };
        System.out.println("Has cycle? " + CycleDetection.detectCycleDirected(directedGraph, 0));

        int[][] undirectedGraph = new int[][] { { 1, 2 }, { 0, 3 }, { 0 }, { 1, 4 }, {} };
        System.out.println("Has cycle? " + CycleDetection.detectCycleUndirected(undirectedGraph, 0));
    }
}