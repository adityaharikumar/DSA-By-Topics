class Solution {
    public int maxResult(int[] nums, int k) {
        
        int n = nums.length;
        int[] dp = new int[n];

        Deque<Integer> deque = new LinkedList<>();

        dp[0] = nums[0];
        deque.offer(0);

        for (int i = 1; i < n; i++) {

            // Remove indices outside the jump range
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Use the maximum dp value
            dp[i] = nums[i] + dp[deque.peekFirst()];

            // Remove smaller dp values from the back
            while (!deque.isEmpty() &&
                   dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return dp[n - 1];
    }
}