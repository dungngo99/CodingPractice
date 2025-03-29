package BitManipulation;

import java.util.*;

/*
 * Solution:
 * 1. Since the input only has alphabets, we maintain int[] array of length 26 for 26 characters
 * 2. If a string is a permutation of palindrome, there is only 1 character with odd frequency, the rest with even frequency
 * 3. We flip bits. If characters have even frequency, their bits are flipped back to zeros
 */
public class PalindromePermutation {
    public static boolean solution(String string) {
        int buffer = 0;
        string = string.toLowerCase(Locale.ROOT);

        for (int i = 0; i < string.length(); i++) {
            int index = string.charAt(i) - 'a';
            buffer = buffer ^ (1 << index);
        }

        if (buffer == 0)
            return true;
        return (buffer & (buffer - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(PalindromePermutation.solution("TactCoa"));
        System.out.println(PalindromePermutation.solution("TTCCAABD"));
    }
}