class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // right[i][j]: paths when approaching this cell moving right
        // down[i][j]: paths when approaching this cell moving down
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                // Reaching the destination completes one valid path.
                if (i == m - 1 && j == n - 1) {
                    right[i][j] = 1;
                    down[i][j] = 1;
                    continue;
                }

                int moveRight = (j + 1 < n) ? right[i][j + 1] : 0;
                int moveDown = (i + 1 < m) ? down[i + 1][j] : 0;

                if (grid[i][j] == 1) {
                    // Mirrors force a direction change.
                    right[i][j] = moveDown;
                    down[i][j] = moveRight;
                } else {
                    // Empty cells allow either direction.
                    int paths = (moveRight + moveDown) % MOD;
                    right[i][j] = paths;
                    down[i][j] = paths;
                }
            }
        }

        // The starting cell is empty, so either state gives the answer.
        return right[0][0];
    }
}