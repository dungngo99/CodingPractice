package org.example.HashTable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinPeopleToTeach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int res = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> langs = new HashMap<>();
        Set<Integer> commonLang = new HashSet<>();

        for (int[] friend: friendships){
            int p1 = friend[0]; int p2 = friend[1];
            int[] p1Lang = languages[p1-1];
            int[] p2Lang = languages[p2-1];
            Set<Integer> p1Set = IntStream.of(p1Lang).boxed().collect(Collectors.toCollection(HashSet::new));
            Set<Integer> p2Set = IntStream.of(p2Lang).boxed().collect(Collectors.toCollection(HashSet::new));

            if (Collections.disjoint(p1Set, p2Set)) {
                for (int lang : p1Set) {
                    Set<Integer> ppl = langs.getOrDefault(lang, null);
                    if (ppl == null) langs.put(lang, new HashSet<>(List.of(p2)));
                    else ppl.add(p2);
                }
                for (int lang : p2Set) {
                    Set<Integer> ppl = langs.getOrDefault(lang, null);
                    if (ppl == null) langs.put(lang, new HashSet<>(List.of(p1)));
                    else ppl.add(p1);
                }

                p1Set.addAll(p2Set);
                if (commonLang.isEmpty()) commonLang = p1Set;
                else commonLang.retainAll(p1Set);
            }
       }

        for (int key: commonLang){
            res = Math.min(res, langs.get(key).size());
        }

        if (res == Integer.MAX_VALUE) return 0;
        return res;
    }

    public static void main(String[] args) {
        MinPeopleToTeach solution = new MinPeopleToTeach();
        int[][] languages = new int[][]{
                {4,7,2,14,6},
                {15,13,6,3,2,7,10,8,12,4,9},{16},{9},{10,3},
                {4,12,8,1,16,5,15,17,13},{4,13,15,8,17,3,6,14,5,10},
                {11,4,13,8,3,14,5,7,15,6,9,17,2,16,12},{4,14,6},
                {16,17,9,3,11,14,10,12,1,8,13,4,5,6},{14},{7,14},
                {17,15,10,3,2,12,16,14,1,7,9,6,4}};

        int[][] friendships = new int[][]{
                {4,11},{3,5},{7,10},{10,12},{5,7},{4,5},{3,8},{1,5},{1,6},{7,8},
                {4,12},{2,4},{8,9},{3,10},{4,7},{5,12},{4,9},{1,4},{2,8},{1,2},
                {3,4},{5,10},{2,7},{1,7},{1,8},{8,10},{1,9},{1,10},{6,7},{3,7},{8,12},{7,9},{9,11},{2,5},{2,3}};
        System.out.println(solution.minimumTeachings(3, languages, friendships));
    }
}