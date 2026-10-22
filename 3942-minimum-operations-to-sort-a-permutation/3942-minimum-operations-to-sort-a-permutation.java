class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        boolean increasing = true;
        boolean decreasing = true;
        int zeroIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) zeroIndex = i;

            int next = nums[(i + 1) % n];

            if (next != (nums[i] + 1) % n) {
                increasing = false;
            }

            if (next != (nums[i] - 1 + n) % n) {
                decreasing = false;
            }
        }

        int answer = Integer.MAX_VALUE;

        if (increasing) {
            // Rotate left directly, or reverse, rotate, reverse.
            answer = Math.min(
                zeroIndex,
                2 + (n - zeroIndex) % n
            );
        }

        if (decreasing) {
            // Reverse then rotate, or rotate then reverse.
            answer = Math.min(answer, Math.min(
                1 + (n - 1 - zeroIndex),
                1 + (zeroIndex + 1) % n
            ));
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}