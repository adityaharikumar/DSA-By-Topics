import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Set<Integer>> dp = new ArrayList<>();

        for (int j = 0; j < n; j++) {
            dp.add(new HashSet<>());
        }

        dp.get(0).add(grid[0][0]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                Set<Integer> current = new HashSet<>();
                int value = grid[i][j];

                // dp[j] still contains results from the cell above.
                if (i > 0) {
                    for (int xor : dp.get(j)) {
                        current.add(xor ^ value);
                    }
                }

                // dp[j - 1] contains results from the cell to the left.
                if (j > 0) {
                    for (int xor : dp.get(j - 1)) {
                        current.add(xor ^ value);
                    }
                }

                dp.set(j, current);
            }
        }

        return Collections.min(dp.get(n - 1));
    }
}