package Array;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public static List<String> partition(String s) {
        List<String> list = new ArrayList<>();

        int[] indices = new int[26];
        for (int i = 0; i < s.length(); i++) {
            indices[s.charAt(i) - 'a'] = i;
        }

        int start = 0;
        int lastIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            lastIndex = Math.max(lastIndex, indices[s.charAt(i) - 'a']);

            if (lastIndex == i) {
                list.add(s.substring(start, i + 1));
                start = i + 1;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String string = "ababcbacadefegdehijhklij";
        System.out.println(PartitionLabels.partition(string));
    }
}