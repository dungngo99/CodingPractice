package org.example.GraphTheory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    public static int[] shortestDistances(int[][][] weightedGraph, int startNode, int N) {
        boolean[] visited = new boolean[N];

        int[] distances = new int[N];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        PriorityQueue<Integer[]> minHeap = new PriorityQueue<>((Integer[] a, Integer[] b) -> a[1] - b[1]);
        minHeap.add(new Integer[] { 0, 0 });

        while (minHeap.size() > 0) {
            Integer[] pair = minHeap.poll();
            int endNode = pair[0];
            int distance = pair[1];

            if (visited[endNode])
                continue;
            visited[endNode] = true;

            for (int[] pairInfo : weightedGraph[endNode]) {
                int neighbor = pairInfo[0];
                int weight = pairInfo[1];
                distances[neighbor] = Math.min(distances[neighbor], distance + weight);
                minHeap.add(new Integer[] { neighbor, distances[neighbor] });
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        int[][][] weightedGraph = new int[][][] {
                { { 1, 1 }, { 2, 4 } },
                { { 2, 2 }, { 3, 6 } },
                { { 3, 3 } },
                {}
        };
        int N = 4;
        int startNode = 0;
        int[] distances = Dijkstra.shortestDistances(weightedGraph, startNode, N);
        for (int i = 0; i < distances.length; i++) {
            System.out.println(String.format("D(%s, %s)=%s", startNode, i, distances[i]));
        }
    }
}
