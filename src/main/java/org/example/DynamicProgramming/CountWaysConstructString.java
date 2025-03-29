package DynamicProgramming;

public class CountWaysConstructString {
    public int findCountConstruct(String target, String[] wordBank) {
        int targetLength = target.length();
        int[] dp = new int[targetLength + 1];
        dp[0] = 1;

        for (int i = 0; i <= targetLength; i++) {
            if (dp[i] == 0)
                continue;

            for (String word : wordBank) {
                int wordLength = word.length();
                if (i + wordLength > targetLength)
                    continue;

                if (target.substring(i, i + wordLength).equals(word)) {
                    dp[i + wordLength] += dp[i];
                }
            }
        }

        return dp[targetLength];
    }

    public static void main(String[] args) {
        CountWaysConstructString countConstruct = new CountWaysConstructString();
        String target = "eee";
        String[] wordBank = new String[] { "e", "ee", "eee", "eeee" };
        System.out.println(countConstruct.findCountConstruct(target, wordBank));
    }
}
