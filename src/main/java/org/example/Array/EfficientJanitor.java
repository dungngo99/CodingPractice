package Array;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/*
 * Idea: we want to have the max number of bags, 
 * each of which has 2 trashes in it (smallest + biggest together)
 */
public class EfficientJanitor {
    public int solution(List<Double> trashes) {
        Collections.sort(trashes);

        int i = 0;
        int j = trashes.size() - 1;
        int res = 0;

        while (i <= j) {
            if (i == j) {
                res += 1;
                break;
            }

            if (trashes.get(i) + trashes.get(j) <= 3.0) {
                i += 1;
                j -= 1;
            } else
                j -= 1;

            res += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        EfficientJanitor sol = new EfficientJanitor();
        List<Double> list = new ArrayList<>(List.of(1.2, 1.1, 1.01, 3.0, 1.99));
        System.out.println(sol.solution(list));
    }
}
