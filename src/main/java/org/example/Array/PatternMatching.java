package org.example.Array;

import java.util.ArrayList;
import java.util.List;

public class PatternMatching {
    public static List<Integer> zalgorithm(String s, String p) {
        List<Integer> indexes = new ArrayList<>();
        String newS = p + "#" + s;
        int[] z = new int[newS.length()];

        int L = 0, R = 0;
        for (int k = 1; k < z.length; k++) {
            if (k > R) {
                R = k;
                L = k;
                while (R < z.length && newS.charAt(R) == newS.charAt(R - L))
                    R++;

                z[k] = R - L;
                R--;
            } else {
                if (k + z[k - L] <= R)
                    z[k] = z[k - L];
                else {
                    L = k;
                    R = k;
                    while (R < z.length && newS.charAt(R) == newS.charAt(R - L))
                        R++;

                    z[k] = R - L;
                    R--;
                }
            }
        }

        for (int i = 0; i < z.length; i++) {
            if (z[i] == p.length())
                indexes.add(i - p.length() - 1);
        }
        return indexes;
    }

    public static void main(String[] args) {
        String s = "HATTIVATTI";
        String p = "ATT";
        List<Integer> indexes = PatternMatching.zalgorithm(s, p);
        System.out.println("A list of indexes is " + indexes);
    }
}
