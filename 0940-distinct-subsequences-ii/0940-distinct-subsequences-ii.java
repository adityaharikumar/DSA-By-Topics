class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int n = s.length();

        long[] dp = new long[n + 1];

        // Empty subsequence
        dp[0] = 1;

        // Last occurrence of each character
        int[] last = new int[26];

        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }

        for (int i = 1; i <= n; i++) {

            int ch = s.charAt(i - 1) - 'a';

            // Double all previous subsequences
            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicates
            if (last[ch] != -1) {
                dp[i] = (dp[i] - dp[last[ch] - 1] + MOD) % MOD;
            }

            // Current character becomes the latest occurrence
            last[ch] = i;
        }

        // Remove empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}