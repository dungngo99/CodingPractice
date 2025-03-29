package org.example.Array;

import java.util.Arrays;
import java.util.List;

/*
 * Find the most visited location in the training sprint. If there are many equal frequent track, choose the smallest location
 * Way 1, traverse each training -> each training can traverse up to the. The size of training sprint -> O(M^2)
 * Way 2, mark start and end different -> when reach end (indicate that we finish 1 training sprint)
 */
public class SprintTraining {
    public static void main(String[] args) {
        SprintTraining sprintTraining = new SprintTraining();
        System.out.println(sprintTraining.solution(Arrays.asList(1, 2, 3, 4), 7));
    }

    public int solution(List<Integer> sprints, int size) {
        int[] tracks = new int[size];

        for (int i = 0; i < sprints.size() - 1; i++) {
            int low = Math.min(sprints.get(i), sprints.get(i + 1));
            int high = Math.max(sprints.get(i), sprints.get(i + 1));
            tracks[low] += 1;
            tracks[high] -= 1;
        }

        int currentScore = 0;
        int[] scores = new int[size];
        for (int i = 0; i < size; i++) {
            currentScore += tracks[i];
            scores[i] = currentScore;
        }

        int freq = 0;
        int res = 0;
        for (int i = 0; i < size; i++) {
            if (freq < scores[i]) {
                freq = scores[i];
                res = i;
            }
        }
        return res;
    }
}
