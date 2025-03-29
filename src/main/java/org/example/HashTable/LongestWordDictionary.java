package HashTable;

import java.util.*;

public class LongestWordDictionary {
    public String findLongestWord(String s, List<String> dictionary) {
        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i=0; i<s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                List<Integer> indexes = map.get(s.charAt(i));
                indexes.add(i);
            }else{
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                map.put(s.charAt(i), indexes);
            }
        }

        int maxLength = 0;
        List<String> maxLst = new ArrayList<>();

        for (int i=0; i<dictionary.size(); i++){
            int cover = -1;
            String str = dictionary.get(i);
            boolean valid = true;
            Map<Character, Integer> store = new HashMap<>();

            for (int j=0; j<str.length(); j++){
                char c = str.charAt(j);

                if (!map.containsKey(c)){
                    valid = false; break;
                }else{
                    List<Integer> indexes = map.get(c);

                    if (store.containsKey(c)){
                        int lastIndex = store.get(c);
                        if (lastIndex+1 >= indexes.size()){
                            valid = false; break;
                        }else{
                            int idx = binarySearch(indexes, cover, lastIndex+1, indexes.size()-1);
                            if (idx == -1){
                                valid = false; break;
                            }else{
                                store.put(c, idx);
                                cover = indexes.get(idx);
                            }
                        }
                    }else{
                        int k = binarySearch(indexes, cover, 0, indexes.size()-1);
                        if (k == -1){
                            valid = false; break;
                        }else{
                            store.put(c, k);
                            cover = indexes.get(k);
                        }
                    }
                }
            }

            if (valid){
                if (maxLength < str.length()){
                    maxLength = str.length();
                    maxLst = new ArrayList<>(List.of(str));
                }else if (maxLength == str.length()){
                    maxLst.add(str);
                }
            }
        }

        maxLst.sort(Comparator.comparing(a -> a));

        if (maxLst.size() == 0) return "";
        return maxLst.get(0);
    }

    public int binarySearch(List<Integer> lst, int key, int l, int h){
        if (l > h) return l;
        else if (l == h){
            if (lst.get(l) > key) return l;
            else {
                if (l+1 == lst.size()) return -1;
                else return l+1;
            }
        }
        int mid = (l+h)/2;
        if (lst.get(mid) > key){
            return binarySearch(lst, key, l, mid-1);
        }else if (lst.get(mid) < key){
            return binarySearch(lst, key, mid+1, h);
        }else{
            if (l+1 == lst.size()) return -1;
            else return l+1;
        }
    }

    public static void main(String[] args) {
        LongestWordDictionary solution = new LongestWordDictionary();
        System.out.println(solution.findLongestWord(
                "abpcpleaalknsckanlancanlscnlnalcbalsvbakvnwopqpcnlcanslkanslkna",
                List.of("applealnclnsck")));
    }
}