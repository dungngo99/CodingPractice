package org.example.UnionFind;

import java.util.Arrays;

public class UnionFind {
    public static int getLeader(int[] union, int node) {
        if (union[node] == node)
            return node;
        if (union[node] < 0)
            return node;
        return getLeader(union, union[node]);
    }

    public static void merge(int[] union, int u, int v) {
        int leaderU = getLeader(union, u);
        int leaderV = getLeader(union, v);

        if (leaderU == leaderV)
            return;

        int rankU = union[leaderU];
        int rankV = union[leaderV];

        if (rankU >= 0 && rankV >= 0) {
            union[leaderU] = -2;
            union[leaderV] = leaderU;
        } else if (-rankU < -rankV) {
            union[leaderV] += rankU < 0 ? rankU : -1;
            union[leaderU] = leaderV;
            while (u != leaderU) {
                int tmp = union[u];
                union[u] = leaderV;
                u = tmp;
            }
        } else if (-rankU >= -rankV) {
            union[leaderU] += rankV < 0 ? rankV : -1;
            union[leaderV] = leaderU;
            while (v != leaderV) {
                int tmp = union[v];
                union[v] = leaderU;
                v = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int N = 10;
        int[] union = new int[N];
        for (int i = 0; i < N; i++)
            union[i] = i;

        System.out.println(Arrays.toString(union));
        UnionFind.merge(union, 2, 6);
        UnionFind.merge(union, 2, 3);
        UnionFind.merge(union, 4, 7);
        UnionFind.merge(union, 3, 7);
        UnionFind.merge(union, 4, 9);
        System.out.println(Arrays.toString(union));
    }
}
