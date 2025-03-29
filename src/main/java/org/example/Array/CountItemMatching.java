package org.example.Array;

import java.util.List;

public class CountItemMatching {
    public static void main(String[] args) {
        CountItemMatching countItemMatching = new CountItemMatching();
        countItemMatching.countMatches(
                List.of(
                        List.of("1", "2"),
                        List.of("3", "4")),
                "halo", "123");
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            switch (ruleKey) {
                case "type":
                    if (ruleValue.equals(items.get(i).get(0)))
                        count += 1;
                    break;
                case "color":
                    if (ruleValue.equals(items.get(i).get(1)))
                        count += 1;
                    break;
                case "name":
                    if (ruleValue.equals(items.get(i).get(2)))
                        count += 1;
            }
        }
        return count;
    }
}