package Stack;

import java.util.Stack;
import java.util.Arrays;

/*
 * Given an array of daily temperatures, compute an array such that
 * each element i is the number of days that day i sees its first warmer temperature
 */
public class DailyTemperature {
    public static int[] dailyTemperatures(int[] T) {
        if (T.length == 0 || T == null)
            return null;

        Stack<Integer[]> stack = new Stack<>();
        stack.push(new Integer[] { T[0], 0 });
        int[] res = new int[T.length];
        Arrays.fill(res, -1);

        for (int i = 1; i < T.length; i++) {
            if (T[i] <= T[i - 1]) {
                stack.push(new Integer[] { T[i], i });
                continue;
            }

            while (stack.size() > 0 && T[i] > stack.peek()[0]) {
                Integer[] list = stack.pop();
                int index = list[1];
                res[index] = i - index;
            }

            stack.push(new Integer[] { T[i], i });
        }
        return res;
    }

    public static void main(String[] args) {
        int[] temperatures = new int[] { 73, 74, 75, 71, 69, 72, 76, 73, 80 };
        int[] res = DailyTemperature.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(res));
    }
}