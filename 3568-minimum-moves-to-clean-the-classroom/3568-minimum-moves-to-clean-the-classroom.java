import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        List<int[]> litter = new ArrayList<>();

        // Find start and litter positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } 
                else if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();
        int targetMask = (1 << k) - 1;

        // litterIndex[r][c] tells which bit belongs to that litter
        int[][] litterIndex = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }

        for (int i = 0; i < k; i++) {
            int r = litter.get(i)[0];
            int c = litter.get(i)[1];
            litterIndex[r][c] = i;
        }

        // best[r][c][mask] = maximum energy remaining
        int[][][] best = new int[m][n][1 << k];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        // {row, col, mask, remainingEnergy, moves}
        queue.offer(new int[]{startR, startC, 0, energy, 0});

        best[startR][startC][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int currEnergy = curr[3];
            int moves = curr[4];

            // All litter collected
            if (mask == targetMask) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Out of bounds
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                // Obstacle
                if (cell == 'X') {
                    continue;
                }

                // Cannot move without energy
                if (currEnergy == 0) {
                    continue;
                }

                int newEnergy = currEnergy - 1;
                int newMask = mask;

                // Collect litter
                if (cell == 'L') {
                    int index = litterIndex[nr][nc];
                    newMask |= (1 << index);
                }

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                // Skip if same state was reached with more energy
                if (best[nr][nc][newMask] >= newEnergy) {
                    continue;
                }

                best[nr][nc][newMask] = newEnergy;

                queue.offer(new int[]{
                    nr, nc, newMask, newEnergy, moves + 1
                });
            }
        }

        return -1;
    }
}