class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < n; j++) {
                row.append(i == 0 || j == n - 1 ? '.' : '#');
            }

            grid[i] = row.toString();
        }

        return grid;
    }
}