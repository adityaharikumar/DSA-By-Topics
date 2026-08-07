class Solution {

    // factors: {2, 3, 5, 7}
    static final int[][] f = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    int[][] dp;

    public String smallestNumber(String num, long t) {

        int[] need = new int[4];

        // Factorize t
        while (t % 2 == 0) {
            need[0]++;
            t /= 2;
        }

        while (t % 3 == 0) {
            need[1]++;
            t /= 3;
        }

        while (t % 5 == 0) {
            need[2]++;
            t /= 5;
        }

        while (t % 7 == 0) {
            need[3]++;
            t /= 7;
        }

        // t contains some prime other than 2,3,5,7
        if (t != 1) {
            return "-1";
        }

        /*
         * Precompute minimum digits required
         * for factors of 2 and 3.
         */
        buildDP(need[0], need[1]);

        // Check num itself
        if (isValid(num, need)) {
            return num;
        }

        int n = num.length();

        /*
         * prefix[i][j] = number of prime j factors
         * in num[0 ... i-1]
         */
        int[][] prefix = new int[n + 1][4];

        int firstZero = n;

        for (int i = 0; i < n; i++) {

            int d = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {
                prefix[i + 1][j] = prefix[i][j];
            }

            if (d == 0) {
                firstZero = Math.min(firstZero, i);
            } else {
                for (int j = 0; j < 4; j++) {
                    prefix[i + 1][j] += f[d][j];
                }
            }
        }

        /*
         * Try to make a number of the SAME length.
         *
         * Change from right to left.
         */
        for (int pos = n - 1; pos >= 0; pos--) {

            /*
             * If there is a zero before pos,
             * prefix cannot remain unchanged.
             */
            if (firstZero < pos) {
                continue;
            }

            int current = num.charAt(pos) - '0';

            /*
             * Try smallest digit greater than current.
             */
            for (int d = current + 1; d <= 9; d++) {

                if (d == 0) {
                    continue;
                }

                int[] remaining = new int[4];

                for (int j = 0; j < 4; j++) {
                    remaining[j] =
                        Math.max(
                            0,
                            need[j] - prefix[pos][j]
                        );
                }

                // Factors supplied by new digit
                for (int j = 0; j < 4; j++) {
                    remaining[j] =
                        Math.max(
                            0,
                            remaining[j] - f[d][j]
                        );
                }

                int slots = n - pos - 1;

                /*
                 * Can suffix satisfy the remaining factors?
                 */
                if (minDigits(remaining) <= slots) {

                    StringBuilder ans = new StringBuilder(n);

                    // Original prefix
                    ans.append(num, 0, pos);

                    // New larger digit
                    ans.append((char) ('0' + d));

                    // Smallest possible suffix
                    buildSmallest(ans, remaining, slots);

                    return ans.toString();
                }
            }
        }

        /*
         * Same length is impossible.
         *
         * Need at least n+1 digits, but possibly
         * even more if t is very large.
         */
        int required = minDigits(need);

        if (required == Integer.MAX_VALUE) {
            return "-1";
        }

        int length = Math.max(n + 1, required);

        StringBuilder ans = new StringBuilder(length);

        buildSmallest(ans, need.clone(), length);

        return ans.toString();
    }

    /*
     * Precompute minimum number of digits needed
     * for powers of 2 and 3.
     */
    private void buildDP(int max2, int max3) {

        dp = new int[max2 + 1][max3 + 1];

        for (int i = 0; i <= max2; i++) {
            for (int j = 0; j <= max3; j++) {
                dp[i][j] = 1000000;
            }
        }

        dp[0][0] = 0;

        /*
         * Only digits that contribute 2/3 are needed.
         */
        int[] digits = {2, 3, 4, 6, 8, 9};

        for (int i = 0; i <= max2; i++) {
            for (int j = 0; j <= max3; j++) {

                if (dp[i][j] == 1000000) {
                    continue;
                }

                for (int d : digits) {

                    int ni = Math.min(
                        max2,
                        i + f[d][0]
                    );

                    int nj = Math.min(
                        max3,
                        j + f[d][1]
                    );

                    dp[ni][nj] =
                        Math.min(
                            dp[ni][nj],
                            dp[i][j] + 1
                        );
                }
            }
        }
    }

    /*
     * O(1)
     *
     * Factors 5 and 7 each need one digit.
     */
    private int minDigits(int[] need) {

        int twoThree = dp[need[0]][need[1]];

        if (twoThree >= 1000000) {
            return Integer.MAX_VALUE;
        }

        return twoThree + need[2] + need[3];
    }

    /*
     * Check whether num itself works.
     */
    private boolean isValid(String num, int[] need) {

        int[] have = new int[4];

        for (char c : num.toCharArray()) {

            int d = c - '0';

            // Zero-free requirement
            if (d == 0) {
                return false;
            }

            for (int j = 0; j < 4; j++) {
                have[j] += f[d][j];
            }
        }

        for (int j = 0; j < 4; j++) {
            if (have[j] < need[j]) {
                return false;
            }
        }

        return true;
    }

    /*
     * Build lexicographically smallest suffix.
     */
    private void buildSmallest(
            StringBuilder ans,
            int[] need,
            int length) {

        while (length > 0) {

            /*
             * Everything satisfied.
             * Remaining digits should be 1.
             */
            if (need[0] == 0 &&
                need[1] == 0 &&
                need[2] == 0 &&
                need[3] == 0) {

                while (length-- > 0) {
                    ans.append('1');
                }

                return;
            }

            /*
             * Try smallest digit.
             */
            for (int d = 1; d <= 9; d++) {

                int[] next = need.clone();

                for (int j = 0; j < 4; j++) {
                    next[j] =
                        Math.max(
                            0,
                            next[j] - f[d][j]
                        );
                }

                /*
                 * After choosing d, can the remaining
                 * positions satisfy the requirement?
                 */
                if (minDigits(next) <= length - 1) {

                    ans.append((char) ('0' + d));

                    need = next;
                    length--;

                    break;
                }
            }
        }
    }
}