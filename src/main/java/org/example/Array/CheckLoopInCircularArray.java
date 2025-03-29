package Array;

import java.util.*;

/* 
 * Given a nested array, we want to check if there is a cycle in that array. Indexes = nodes; array[i] = steps
 * array[i] < 0 means step backward, array[i] > 0 means step forward
 * A cycle is valid if #nodes > 1 && all array[i] has the same sign (positive or negative)        
 * Idea: 
 * 1. We consider each index i as a starting point -> traverse it until cycle (existing index) or meet unsatisfactory cycles
 * 2. We need to use a boolean[] visited to record visited nodes (indexes). If a node exists in a path, no need to consider it again
 * because it will lead to an invalid path
 */
public class CheckLoopInCircularArray {
    public boolean solution(int[] nums) {
        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;

            int cur = i;
            HashMap<Integer, Integer> pointers = new HashMap<>();
            int sign = nums[cur] > 0 ? 1 : -1;
            boolean valid = true;

            while (!pointers.containsKey(cur)) {
                if (sign * nums[cur] < 0) {
                    valid = false;
                    break;
                }

                visited[cur] = true;
                int steps = (cur + nums[cur]) % nums.length;
                int nextCur = steps > 0 ? steps : nums.length + steps;
                pointers.put(cur, nextCur);
                cur = nextCur;
            }
            if (valid && pointers.get(cur) != i)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CheckLoopInCircularArray solution = new CheckLoopInCircularArray();
        System.out.println(solution.solution(new int[] { -1, 2, 1, 2 }));
    }
}