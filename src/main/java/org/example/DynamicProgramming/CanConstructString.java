package DynamicProgramming;

public class CanConstructString {
    public static boolean bottomUp(String target, String[] wordBank) {
        int targetLength = target.length();
        boolean[] dp = new boolean[target.length() + 1];
        dp[0] = true;

        for (int i = 0; i <= targetLength; i++) {
            if (!dp[i])
                continue;

            for (String word : wordBank) {
                int wordLength = word.length();
                if (i + wordLength > targetLength)
                    continue;

                String targetSubstring = target.substring(i, i + wordLength);
                if (word.equals(targetSubstring)) {
                    dp[i + wordLength] = true;
                }
            }
        }
        return dp[targetLength];
    }

    public static void main(String[] agrs) {
        String string = "abdf";
        String[] wordBank = new String[] { "ab", "abc", "cd", "def", "abcd", "df"};
        System.out.println(CanConstructString.bottomUp(string, wordBank));
    }
}
