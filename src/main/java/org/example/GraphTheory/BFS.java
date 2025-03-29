package GraphTheory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFS {
    public static void traverseBFS(int[][] graph, int startNode) {
        LinkedList<Integer> frontiers = new LinkedList<>(List.of(startNode));
        Set<Integer> visited = new HashSet<>(Set.of(startNode));

        while (frontiers.size() > 0) {
            int node = frontiers.removeFirst();
            System.out.println("This is node " + node);
            for (int neighbor : graph[node]) {
                if (!visited.contains(neighbor)) {
                    frontiers.addLast(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public static void traverseBFSBySetUnion(int[][] graph, int startNode) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> frontiers = new HashSet<>(Set.of(startNode));

        int layer = 0;
        while (frontiers.size() > 0) {
            System.out.println(String.format("Layer %s: %s", layer++, frontiers.toString()));

            Set<Integer> buffer = new HashSet<>();
            for (int node: frontiers){
                List<Integer> tmp = Arrays.stream(graph[node]).boxed().toList();
                buffer.addAll(tmp);
            }
            visited.addAll(frontiers);
            buffer.removeAll(visited);
            frontiers = buffer;
        }
    }

    public static Map<Integer, Integer> findDistance(int[][] graph, int startNode) {
        Map<Integer, Integer> distance = new HashMap<>(Map.of(startNode, 0));
        LinkedList<Integer> frontiers = new LinkedList<>(List.of(0));
        Set<Integer> visited = new HashSet<>(Set.of(0));

        while (frontiers.size() > 0) {
            int node = frontiers.removeFirst();
            System.out.println(String.format(
                    "Distance from starting node %d to node %d is %d",
                    startNode, node, distance.get(node)));

            for (int neighbor : graph[node]) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    frontiers.addLast(neighbor);
                    distance.put(neighbor, distance.get(node) + 1);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] { { 1, 3 }, { 0, 4, 2 }, { 1, 4 }, { 0 }, { 1, 2 } };
        traverseBFS(graph, 0);
        traverseBFSBySetUnion(graph, 1);

        Map<Integer, Integer> distance = findDistance(graph, 0);
        System.out.println(distance);
    }
}
