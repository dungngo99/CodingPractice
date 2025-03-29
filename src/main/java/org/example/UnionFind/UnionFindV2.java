package org.example.UnionFind;

import java.util.*;

public class UnionFindV2 {
    public static boolean isLeader(int[] union, int node) {
        return union[node] <= -1;
    }

    public static int getLeader(int[] union, int node) {
        if (UnionFindV2.isLeader(union, node))
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

        if (-rankU >= -rankV) {
            union[leaderU] += union[leaderV];
            union[leaderV] = leaderU;
            while (v != leaderV) {
                int tmp = union[v];
                union[v] = leaderU;
                v = tmp;
            }
        } else {
            union[leaderV] += union[leaderU];
            union[leaderU] = leaderV;
            while (u != leaderU) {
                int tmp = union[u];
                union[u] = leaderV;
                u = tmp;
            }
        }
    }

    public static int numLeaders(int[] union) {
        int count = 0;
        for (int i = 0; i < union.length; i++) {
            if (union[i] <= -1)
                count++;
        }
        return count;
    }

    public static Map<Integer, List<Integer>> getUnions(int[] union) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < union.length; i++) {
            if (UnionFindV2.isLeader(union, i)) {
                if (map.containsKey(i))
                    map.get(i).add(i);
                else
                    map.put(i, new ArrayList<>(List.of(i)));
            } else {
                int leader = UnionFindV2.getLeader(union, i);
                if (map.containsKey(leader))
                    map.get(leader).add(i);
                else
                    map.put(leader, new ArrayList<>(List.of(i)));
            }
        }
        return map;
    }

    public static void main(String[] args) {
        int N = 10;
        int[] union = new int[N];
        Arrays.fill(union, -1);

        System.out.println(Arrays.toString(union));
        UnionFind.merge(union, 2, 6);
        UnionFind.merge(union, 2, 3);
        UnionFind.merge(union, 4, 7);
        UnionFind.merge(union, 3, 7);
        UnionFind.merge(union, 4, 9);
        System.out.println(Arrays.toString(union));

        System.out.println("Number of unions is " + UnionFindV2.numLeaders(union));

        System.out.println("Group structure:");
        System.out.println(UnionFindV2.getUnions(union));
    }
}
