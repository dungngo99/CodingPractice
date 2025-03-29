package org.example.GraphTheory;

import org.example.UnionFind.UnionFind;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KClusteringHamming {
    private int spacing;
    private int numBit;
    private int N;
    private int[] union;
    private Map<Integer, Integer> Index2Hamming;
    private Map<Integer, List<Integer>> Hamming2Indices;
    private Set<Integer> pool;

    public KClusteringHamming(String path, int spacing) {
        this.spacing = spacing;
        int[] header = this.readHeader(path);
        this.N = header[0];
        this.numBit = header[1];
        this.union = new int[this.N];
        this.Index2Hamming = new HashMap<>(this.N);
        this.Hamming2Indices = new HashMap<>(this.N);
        this.pool = new HashSet<>();
    }

    public int[] readHeader(String path) {
        FileReader file = null;
        Scanner scanner = null;

        try {
            file = new FileReader(path);
            scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                String[] header = scanner.nextLine().split(" ");
                int N = Integer.parseInt(header[0]);
                int numBit = Integer.parseInt(header[1]);
                System.out.println(String.format("#Bits:%s - N:%s", numBit, N));
                return new int[] { N, numBit };
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null)
                scanner.close();
        }
        return null;
    }

    public void readContent(String path) {
        FileReader file = null;
        Scanner scanner = null;

        try {
            file = new FileReader(path);
            scanner = new Scanner(file);
            scanner.nextLine();

            int i = 0;
            while (scanner.hasNextLine()) {
                String[] binary = scanner.nextLine().split(" ");
                int num = KClusteringHamming.Binary2Int(binary);
                this.union[i] = i;
                this.Index2Hamming.put(i, num);

                if (this.Hamming2Indices.containsKey(num))
                    this.Hamming2Indices.get(num).add(i);
                else {
                    this.Hamming2Indices.put(num, new ArrayList<>(List.of(i)));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null)
                scanner.close();
        }
    }

    public void createPool() {
        String[] binary = new String[this.numBit];
        Arrays.fill(binary, "0");

        for (int i = 0; i <= this.spacing; i++) {
            createPollHelper(0, 0, i, binary);
        }
    }

    public void createPollHelper(int depth, int index, int targetDepth, String[] binary) {
        if (depth == targetDepth) {
            int num = KClusteringHamming.Binary2Int(binary);
            this.pool.add(num);
            return;
        }
        for (int bit = index; bit < this.numBit; bit++) {
            binary[bit] = "1";
            createPollHelper(depth + 1, bit + 1, targetDepth, binary);
            binary[bit] = "0";
        }
    }

    public void clusterHamming() {
        for (int node = 0; node < this.N; node++) {
            if (union[node] != node)
                continue;

            int nodeHamming = this.Index2Hamming.get(node);
            for (int ops : this.pool) {
                int anotherNodeHamming = nodeHamming ^ ops;
                if (this.Hamming2Indices.containsKey(anotherNodeHamming)) {
                    for (int anotherNode : this.Hamming2Indices.get(anotherNodeHamming)) {
                        UnionFind.merge(this.union, node, anotherNode);
                    }
                }
            }
        }
    }

    public Set<Integer> getClusters() {
        Set<Integer> leaders = new HashSet<>();
        for (int i = 0; i < union.length; i++) {
            leaders.add(UnionFind.getLeader(this.union, i));
        }
        return leaders;
    }

    @Override
    public String toString() {
        return "KClustering2 [Hamming2Indices=" + Hamming2Indices + ", Index2Hamming=" + Index2Hamming + ", N=" + N
                + ", numBit=" + numBit + ", pool=" + pool + ", spacing=" + spacing + ", union=" + Arrays.toString(union)
                + "]";
    }

    public static int Binary2Int(String[] binary) {
        int res = 0;
        int l = binary.length;
        for (int i = l - 1; i >= 0; i--) {
            int bin = Integer.parseInt(binary[i]);
            res += bin * (int) Math.pow(2, l - i - 1);
        }
        return res;
    }

    public static int hammingDistance(int u, int v) {
        int mask = u ^ v;
        int count = 0;
        while (mask > 0) {
            if ((mask & 1) == 1)
                count++;
            mask = mask >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        KClusteringHamming kClustering2 = new KClusteringHamming("src/Data/clustering_big_2.txt", 2);
        kClustering2.readContent("src/Data/clustering_big_2.txt");
        kClustering2.createPool();

        kClustering2.clusterHamming();
        Set<Integer> leaders = kClustering2.getClusters();
        System.out.println(String.format("Leader group's size=%s", leaders.size()));
    }
}
