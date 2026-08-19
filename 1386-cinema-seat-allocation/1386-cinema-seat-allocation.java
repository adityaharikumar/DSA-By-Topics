class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);
        }

        // Initially, every row can fit 2 families
        int ans = (n - map.size()) * 2;

        // Check only rows having reserved seats
        for (int row : map.keySet()) {

            HashSet<Integer> reserved = map.get(row);

            boolean left = !reserved.contains(2)
                        && !reserved.contains(3)
                        && !reserved.contains(4)
                        && !reserved.contains(5);

            boolean middle = !reserved.contains(4)
                           && !reserved.contains(5)
                           && !reserved.contains(6)
                           && !reserved.contains(7);

            boolean right = !reserved.contains(6)
                          && !reserved.contains(7)
                          && !reserved.contains(8)
                          && !reserved.contains(9);

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}