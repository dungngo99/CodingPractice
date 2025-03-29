package org.example.Array;

/*
 * Problem: given two strings s1 and s2, check if s2 is a rotation of s1
 * For example, s1=xy, s2 = yx+yx =yxyx
 */
public class CheckStringRotation {
    public static boolean isSubstring(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        s2 = s2 + s2;
        int j = 0;

        while (j < s2.length()) {
            int length = 0;
            int i = 0;

            while (i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
                length++;
            }

            if (length == s1.length())
                return true;

            j++;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "water bottle";
        String s2 = "wbottlewater";
        System.out.println(CheckStringRotation.isSubstring(s1, s2));
    }
}