package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CoinChange {
    private static final int CONSTRAINTS = (int) Math.pow(10, 4);

    public int computeCoinChange(int[] coins, int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -2);
        int res = helper(dp, coins, amount);

        if (res >= CONSTRAINTS) return -1;
        return res;
    }

    public int helper(int[] dp, int[] coins, int amt){
        if (amt == 0) return 0;
        if (amt < 0) return CONSTRAINTS;
        if (dp[amt] != -2) return dp[amt];

        int[] t = new int[coins.length];
        for (int i = 0; i < coins.length; i++){
            t[i] = 1 + helper(dp, coins, amt-coins[i]);
        }
        dp[amt] = Arrays.stream(t).min().getAsInt();
        return dp[amt];
    }

    public static void main(String[] args){
        int[] coins = {1,2,5};
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.computeCoinChange(coins, 99));

        ArrayList<Integer> lst = new ArrayList<>(10);
        lst.add(1);
        lst.add(2);
        Iterator<Integer> itr = lst.iterator();
        while (itr.hasNext()){
            int i = itr.next();
            System.out.println(i);
            if (i % 2 == 0) itr.remove();
        }
    }
}
