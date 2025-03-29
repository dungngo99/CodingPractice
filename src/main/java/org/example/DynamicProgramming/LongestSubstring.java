package org.example.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;

        int slow = 0;
        for (int fast=0; fast<s.length(); fast++){
            char c = s.charAt(fast);
            if (map.containsKey(c) && map.get(c) >= slow) {
                slow = map.get(c) + 1;
            }
            map.put(s.charAt(fast), fast);
            count = Math.max(count, fast-slow+1);
        }
        return count;
    }

    public static void main(String[] args) {
        LongestSubstring solution = new LongestSubstring();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring("lkelwnnslknas"));
    }
}