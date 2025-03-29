package org.example.GraphTheory;

import org.example.UnionFind.UnionFind;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Kruskal {
    private int N;
    private int[] union;
    List<Integer[]> edges;

    public Kruskal(String path) {
        this.N = readHeader(path);
        this.union = new int[N + 1];
        for (int i = 1; i <= N; i++)
            this.union[i] = i;
        this.edges = new ArrayList<>();
    }

    public int readHeader(String path) {
        Scanner scanner = null;
        FileReader file = null;

        try {
            file = new FileReader(path);
            scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                String N = scanner.nextLine();
                return Integer.parseInt(N);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public void readContent(String path) {
        FileReader file = null;
        Scanner scanner = null;

        try {
            file = new FileReader(path);
            scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                int weight = Integer.parseInt(line[2]);
                this.edges.add(new Integer[] { u, v, weight });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer[]> cluster() {
        List<Integer[]> MST = new ArrayList<>();
        Collections.sort(edges, (Integer[] a, Integer[] b) -> a[2] - b[2]);

        for (Integer[] edge : edges) {
            System.out.println(Arrays.toString(edge));
            int u = edge[0];
            int v = edge[1];
            if (UnionFind.getLeader(this.union, u) != UnionFind.getLeader(this.union, v)) {
                UnionFind.merge(union, u, v);
                MST.add(edge);
            }
        }
        return MST;
    }

    public int distances(List<Integer[]> edges) {
        int distance = 0;
        for (Integer[] edge : edges)
            distance += edge[2];
        return distance;
    }

    public List<List<Integer>> constructGraph(List<Integer[]> mst) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= this.N; i++)
            graph.add(new ArrayList<>());

        for (Integer[] edge : mst) {
            int u = edge[0];
            int v = edge[1];

            if (graph.get(u) == null)
                graph.add(u, new ArrayList<>(List.of(v)));
            else
                graph.get(u).add(v);

            if (graph.get(v) == null)
                graph.add(v, new ArrayList<>(List.of(u)));
            else
                graph.get(v).add(u);
        }
        return graph;
    }

    public void printGraph(List<List<Integer>> edges) {
        for (int i = 1; i < edges.size(); i++) {
            System.out.println(String.format("Node=%s", i));
            System.out.println(edges.get(i));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal("src/Data/clustering_small_p1.txt");
        kruskal.readContent("src/Data/clustering_small_p1.txt");

        List<Integer[]> mst = kruskal.cluster();
        int distance = kruskal.distances(mst);

        System.out.println("Total distances of MST is " + distance);

        List<List<Integer>> graph = kruskal.constructGraph(mst);
        kruskal.printGraph(graph);
    }
}
