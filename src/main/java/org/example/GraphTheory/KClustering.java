package GraphTheory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import UnionFind.UnionFind;

public class KClustering {
    private List<Integer[]> edges;
    private int[] union;
    private int N;

    public KClustering(String path) {
        this.edges = new ArrayList<>();
        this.N = readHeader(path);
        this.union = new int[N + 1];
        for (int i = 1; i <= N; i++)
            this.union[i] = i;
    }

    public int readHeader(String path) {
        FileReader file = null;
        Scanner scanner = null;

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
                String[] string = scanner.nextLine().split(" ");
                int u = Integer.parseInt(string[0]);
                int v = Integer.parseInt(string[1]);
                int weight = Integer.parseInt(string[2]);
                this.edges.add(new Integer[] { u, v, weight });
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
    }

    public int kCluster(int k) {
        Collections.sort(this.edges, (Integer[] a, Integer[] b) -> a[2] - b[2]);

        int lowBound = 1;
        int highBound = this.edges.get(this.edges.size() - 1)[2];

        while (lowBound < highBound) {
            int spacing = (lowBound + highBound) / 2;

            for (int i = 0; i <= this.N; i++)
                this.union[i] = i;

            for (Integer[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                if (weight <= spacing)
                    UnionFind.merge(this.union, u, v);
            }

            if (numClusters() >= k)
                lowBound = spacing + 1;
            else
                highBound = spacing - 1;
        }

        return lowBound;
    }

    public int numClusters() {
        int k = 0;
        for (int i = 1; i < this.union.length; i++) {
            if (this.union[i] == i || this.union[i] < 0)
                k++;
        }
        return k;

    }

    public void printEdges() {
        for (Integer[] edge : this.edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public static void main(String[] args) {
        String path = "/Users/dylanngo/Documents/Software Engineering/Algorithms/Leetcode/Java/stanford-algs/testCases/course3/assignment2Clustering/question1/input_completeRandom_24_256.txt";
        KClustering kClustering = new KClustering(path);
        kClustering.readContent(path);

        int maxSpacing = kClustering.kCluster(4);
        System.out.println("The maximum spacing is " + maxSpacing);
    }

}
