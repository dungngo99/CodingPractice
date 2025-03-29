package org.example.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllCombinationsToString {
    public static Set<List<String>> findAllConstruct(String target, String[] wordBank) {
        int targetLength = target.length();

        List<Set<List<String>>> dp = new ArrayList<>();
        for (int i = 0; i <= targetLength; i++)
            dp.add(new HashSet<>());
        dp.get(0).add(List.of());

        for (int i = 0; i <= targetLength; i++) {
            if (dp.get(i).size() == 0)
                continue;

            Set<List<String>> currentSet = dp.get(i);
            for (String word : wordBank) {
                int wordLength = word.length();
                if (i + wordLength > targetLength)
                    continue;

                if (target.substring(i, i + wordLength).equals(word)) {
                    for (List<String> list : currentSet) {
                        List<String> newList = new ArrayList<>(list);
                        newList.add(word);
                        dp.get(i + wordLength).add(newList);
                    }
                }
            }
        }
        return dp.get(targetLength);
    }

    public static void main(String[] args) {
        String target = "abcdefabc";
        String[] wordBank = new String[] { "ab", "abc", "cd", "def", "abcd", "ef", "c" };

        target = "aaaaaaa";
        wordBank = new String[] { "a", "aa", "aaa", "aaaa" };
        Set<List<String>> res = AllCombinationsToString.findAllConstruct(target, wordBank);
        System.out.println(res);
    }
}
